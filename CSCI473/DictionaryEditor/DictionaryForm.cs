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
 * Version: 4.0.0.0
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
    Headword theHeadword;

    private SqlConnection connection;

    public DictionaryForm()
    {
      CheckForIllegalCrossThreadCalls = false;
      InitializeComponent();  //Initialize all Form components.
      spellingChecker = new SpellCheck();  //Create a new spell checker object.
      theHeadword = new Headword();

      setupSQL();

      // Create a SQL DATABASE if one doesn't exist;

      // Create a clock for the application.
      clockSetup();

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

    public Headword Headword
    {
      get { return theHeadword; }
      set { theHeadword = value; }
    }
    public ComboBox CB_PartOfSpeech
    {
        get { return cb_PartOfSpeech; }
    }

    private void setupSQL()
    {
      String sqlConnString = "server=dictionary3;uid=net3;pwd=otddb3;database=";
      connection.ConnectionString = sqlConnString;
      connection.Open();
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

    // Gets a timer and paints it on the DictionaryForm.
    private void clockSetup()
    {
      Timer timer = new Timer();
      timer.Start();  // Start the timer.
      timer.Interval = 1000; // Set the interval to 1 second.
      timer.Tick += delegate // Add an event handler to the Tick event.
      {
        // Call getTime to get and format the time.
        // Also, change the display.
        string time = DateTime.Now.ToString("hh:mm:ss tt");
        l_clock.Text = time; // Set the time on the Form.
      };
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


    private void btn_wordsOnOff_Click(object sender, EventArgs e)
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
      // Check to see if there is a word.
      if (tb_HeadWord.Text != "")
      {
        theHeadword.HeadwordProp = tb_HeadWord.Text;
        new DefinitionForm(this, theHeadword.HeadwordProp).ShowDialog();
      }
      else
      {
        MessageBox.Show("Please enter a Headword to add a Definition.","Error",MessageBoxButtons.OK);
      }
    }

    // Add the word to the database of the dictionary.
    private void btn_AddEntry_Click(object sender, EventArgs e)
    {
      // If there is a valid word to add to the dictionary.
      if (theHeadword.Definitions.Count > 0
          && theHeadword.Illustrations.Count > 0
          && cb_PartOfSpeech.Text != "")
      {
        // Fill the word with the remaing data.
        theHeadword.PartOfSpeech = cb_PartOfSpeech.Text;
        theHeadword.Pronunciation = tb_Pronunciation.Text;

        // Concatonate the semantic and social strings.
        String[] semanticArray = new String[lb_Semantics.Items.Count];
        lb_Semantics.SelectedItems.CopyTo(semanticArray, 0);
        theHeadword.Semantics = String.Join(",", semanticArray);

        String[] socialArray = new String[lb_SocialUsage.Items.Count];
        lb_Semantics.SelectedItems.CopyTo(socialArray, 0);
        theHeadword.SocialUsage = String.Join(",", socialArray);

        // Add WORD to database...
        MessageBox.Show("Word: \"" + theHeadword.HeadwordProp + "\"\nProunuciation: \"" + theHeadword.Pronunciation
            + "\"\nIllustration: \"" + theHeadword.Illustrations[0] + "\nSocialUsage: \"" + theHeadword.SocialUsage
            + "\n","Error",MessageBoxButtons.OK);

        // Turn off the addEntry and addDefinition buttons.
        btn_AddDefinition.Enabled = false;
        btn_AddEntry.Enabled = false;
      }
      else
      {
        MessageBox.Show("Please add a definition, an illustration or select a POS",
                        "Error", MessageBoxButtons.OK);
      }
    }

    // Clears the user enterable component on the Form.
    private void btn_Clear_Click(object sender, EventArgs e)
    {
        // Clears the user enterable fields.
        tb_HeadWord.Text = "";
        tb_HeadWord.Focus();
        tb_Pronunciation.Text = "";
        lb_Semantics.SelectedIndex = -1;
        lb_SocialUsage.SelectedIndex = -1;
        cb_PartOfSpeech.SelectedIndex = -1;

        // Create a new instace of a headword.
        theHeadword = new Headword();

        // Enable the addDefinition and addEntry buttons.
        btn_AddDefinition.Enabled = true;
        btn_AddEntry.Enabled = true;
    }
  }
}