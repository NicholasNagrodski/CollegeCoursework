using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace DictionaryEditor
{
  public partial class DefinitionForm : Form
  {
    Definition definition;
    Headword theHeadword;  // A reference to the headword in the main program.
       
    public DefinitionForm(DictionaryForm dictionaryEditorForm, String strHeadword)
    {
      InitializeComponent();
      theHeadword = dictionaryEditorForm.Headword;
      tb_Headword.Text = strHeadword;

      // Create a new Definition with the a new ID number;
      definition = new Definition(theHeadword.Definitions.Count + 1);

      // If the user selected a POS for the headword use it.
      if (dictionaryEditorForm.CB_PartOfSpeech.Text != "")
          cb_POS.SelectedIndex = dictionaryEditorForm.CB_PartOfSpeech.SelectedIndex;
    }

    // Controls adding an illustration to a definition and to a headword.
    private void btn_AddIllustration_Click(object sender, EventArgs e)
    {
      // Make sure there is a short definition before adding any illustrations.
      if (tb_ShortDef.Text != "")
      {
        // Open up a dialog box to add a new Illustration to our defintion.
        // If the user presses OK then store the information in the current illustration.
        IllustrationForm newIllustrationForm = new IllustrationForm(this, tb_ShortDef.Text);
        if (newIllustrationForm.ShowDialog(this) == DialogResult.OK )
        {
          if (newIllustrationForm.Translation == "" || newIllustrationForm.Illustration == "")
          {
            MessageBox.Show("Please enter both fields before pressing enter","Error",MessageBoxButtons.OK);
          }
          else
          {
            // Create a new Illustration with a unique ID number, get the illustration and translation.
            Illustration newIllustration = new Illustration(theHeadword.Illustrations.Count + 1);
            newIllustration.IllDescription = newIllustrationForm.Illustration;
            newIllustration.Translation = newIllustrationForm.Translation;

            // Add remaining data to the illustration.
            newIllustration.Headword = tb_Headword.Text;

            // Add the illustration to theHeadword object and the definition.
            definition.Illustrations.Add(newIllustration);

            tb_NumberOfIllustrations.Text = theHeadword.Illustrations.Count.ToString();
          }

          // Close the window.
          newIllustrationForm.Dispose();
        }
      }
      else
      {
        MessageBox.Show("Enter a Short Definition before adding any Illustrations",
                        "Error", MessageBoxButtons.OK);
      }
    }

    private void btn_OK_Click(object sender, EventArgs e)
    {
      if (tb_Definition.Text != "")
      {
        // Add all the data to the definition.
        definition.HeadwordProp = tb_Headword.Text;
        definition.PartOfSpeech = cb_POS.Text;
        definition.ShortDefinition = tb_ShortDef.Text;
        definition.DefinitionProp = tb_Definition.Text;

        // Add the definition to the Headword object in DictionaryForm.
        theHeadword.Definitions.Add(definition);
        theHeadword.Illustrations = definition.Illustrations;

        this.Close();
      }
    }

    private void btn_Cancel_Click(object sender, EventArgs e)
    {
      this.Close();
    }
  }
}
