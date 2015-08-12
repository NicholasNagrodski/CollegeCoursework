/**
 * Assignemt Part: 3
 * DictionaryForm.cs
 *
 * Description: This program implements a GUI for a dictionary editor program,
 *  it links to SpellChecker.dll to implement the background work of the dictionary.
 *
 * Changes:
 * 
 *
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI473 - .NET
 * Prof: Zerwekh
 * Due Date: 2011/03/07
 * Version: 3.0.0.0
 *
 */
using System;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Collections.Generic;
using System.Windows.Forms;
using SpellChecker;
using SemanticSocialAssister;

namespace DictionaryEditor
{
  public partial class DictionaryForm : Form
  {
    // Main classes used.
    SpellCheck spellingChecker;
    SemanticSocial semanticSocial;
    public Headword theHeadword;
    Timer timer;

    public DictionaryForm()
    {
      InitializeComponent();  //Initialize all Form components.
      spellingChecker = new SpellCheck();  //Create a new spell checker object.
      theHeadword = new Headword();

      CheckForIllegalCrossThreadCalls = false;

      // Create a clock for the application.
      timer = new Timer();
      timer.Start();  // Start the timer.
      timer.Interval = 1000; // Set the interval to 1 second.
      timer.Tick += delegate // Add an event handler to the Tick event.
      {
        // Call getTime to get and format the time.
        // Also, change the display.
        string time = DateTime.Now.ToString("hh:mm:ss tt");
        l_clock.Text = time; // Set the time on the Form.
      };

      // Initialize semantic and social fields by reading
      // in the values from the class and populating the ComboBoxes.
      semanticSocial = new SemanticSocial();
      for (int i = 0; i < semanticSocial.ListCount; i++)
      {
        lb_Semantics.Items.Add(semanticSocial[i].Semantic);
        lb_SocialUsage.Items.Add(semanticSocial[i].Social);
      }

      // Set the first item to be selected/highlighted in the GUI.   
      lb_Semantics.SelectedIndex = 0;
      lb_SocialUsage.SelectedIndex = 0;
    }

    // This function responds to an event and updates the GUI fields with all the
    // the information needed from the WordEventArgs fields.
    private void setWordInformation(Object o, WordEventArgs wea)
    {
      // Change the color to red, sleep for a second, then change it back.
      Color originalColor = btn_wordsOnOff.BackColor;
      btn_wordsOnOff.BackColor = Color.Red;
      System.Threading.Thread.Sleep(1000);
      btn_wordsOnOff.BackColor = originalColor;

      // Change all the GUI fields to reflect the new word.
      cb_PartOfSpeech.SelectedIndex = cb_PartOfSpeech.FindString(wea.Pos1);
      tb_HeadWord.Text = wea.Headword;
      tb_Pronunciation.Text = wea.Pronunciation;
      tb_CrossReference.Lines = wea.CrossReferences.ToArray();

      // Set the selections for the semantic and socialUsage listboxes.
      // Select none if there is no listing.
      lb_Semantics.SelectedIndex = -1;
      lb_SocialUsage.SelectedIndex = -1;
      wea.SemainticFields.ForEach(delegate(string str)
      {
        lb_Semantics.SelectedItem = str;
      });
      wea.SocialUsage.ForEach(delegate(string str)
      {
        lb_SocialUsage.SelectedItem = str;
      });
    }

    // Used to paint custom graphics.
    protected override void OnPaint(PaintEventArgs pea)
    {
      // Call the base method and get the graphics.
      base.OnPaint(pea);
      Graphics g = pea.Graphics;

      // Draw our custom title.
      string titleText = "Dictionary Editor";
      Font titleFont = new Font(new FontFamily("Arial"), 38, FontStyle.Italic);
      // Calculate an x Position as to center the title.
      float xPosition = (ClientSize.Width - g.MeasureString(titleText, titleFont).Width) / 2;
      g.DrawString(titleText, titleFont, Brushes.DarkSlateBlue, xPosition, 10);

      // Draw our custom border.
      // First define a bounding polygon of Points for the upper and lower.
      // Then draw the two polygons.
      const int offset = 10;
      Point[] lowerShadowPts = new Point[6] {
        new Point(0,ClientSize.Height),
        new Point(ClientSize.Width,ClientSize.Height),
        new Point(ClientSize.Width,0),
        new Point(ClientSize.Width-offset,offset),
        new Point(ClientSize.Width-offset,ClientSize.Height-offset),
        new Point(offset,ClientSize.Height-offset) };
      Point[] upperShadowPts = new Point[6] {
        new Point(0,0),
        new Point(0,ClientSize.Height),
        new Point(offset,ClientSize.Height-offset),
        new Point(offset,offset),
        new Point(ClientSize.Width-offset,offset),
        new Point(ClientSize.Width,0) };
      g.FillPolygon(Brushes.DarkSeaGreen, upperShadowPts);
      g.FillPolygon(Brushes.ForestGreen, lowerShadowPts);
    }

    // When the Spell Check button is clicked, we want to perform a spell check and
    // we create an instance of the SpellCheck class which contains the library of
    // words we wish to search from.  
    private void btn_SpellCheck_Click(object sender, EventArgs e)
    {
      try
      {
        // Call the spellChecker with the word converted to lower case.
        MessageBox.Show(spellingChecker.CheckSpelling(tb_HeadWord.Text.ToLower()));
      }
      catch (SpellException se)
      {
        // Get the custom message for the SpellingException.
        if (se.InnerException != null)
        {
          MessageBox.Show("Sender: " + se.Sender
            + "\nMessage: " + se.Message,
              "Inner Exception: " + se.InnerException.Message);
        }
        else
        {
          MessageBox.Show(se.Message, "SpellingException");
        }
      }
    }

    // Controls Exit button functionality.
    private void btn_Exit_Click(object sender, EventArgs e)
    {
      this.Close(); // Close the form.
    }

    // Call OnPaint when resized to update drawings...
    private void DictionaryForm_SizeChanged(object sender, EventArgs e)
    {
      // Call OnPaint when resized to update drawings...
    }


    void btn_wordsOnOff_Click(object sender, EventArgs e)
    {
      if (btn_wordsOnOff.Text == "Turn Words On")
      {
        spellingChecker.WordEventArgsEvent += new SpellCheck.WordEventArgsEventHandler(setWordInformation);
        btn_wordsOnOff.Text = "Turn Words Off";
      }
      else if (btn_wordsOnOff.Text == "Turn Words Off")
      {
        spellingChecker.WordEventArgsEvent -= setWordInformation;
        btn_wordsOnOff.Text = "Turn Words On";
      }
    }

    private void btn_AddDefinition_Click(object sender, EventArgs e)
    {
      if (tb_HeadWord.Text != null && cb_PartOfSpeech.Text != null)
      {
        new DefinitionForm(this, theHeadword).Show();
      }
    }

    private void btn_Clear_Click(object sender, EventArgs e)
    {
      tb_HeadWord.Text = "";
      tb_HeadWord.Focus();
      tb_Pronunciation.Text = "";

      lb_Semantics.SelectedIndex = -1;
      lb_SocialUsage.SelectedIndex = -1;

      theHeadword = new Headword();
    }
  }
}