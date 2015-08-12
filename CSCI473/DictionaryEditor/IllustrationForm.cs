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
  public partial class IllustrationForm : Form
  {
    DefinitionForm definitionForm;

    public IllustrationForm(DefinitionForm definitionForm, String definitionText)
    {
      InitializeComponent();
      tb_Definition.Text = definitionText;
      this.definitionForm = definitionForm;
      tb_Illustration.Focus();
    }

    // Properties to allow DefinitionForm to read from this.
    public String Illustration 
    {
      get { return tb_Illustration.Text; }
    }
    public String Translation
    {
      get { return tb_Translation.Text;  }
    }

    // Use this overridding event handler for the FormClosing event.
    // We want to hide the form so the DefinitionForm can retreive data not close the Form.
    private void MyForm_FormClosing(object sender, FormClosingEventArgs e)
    {
        this.Hide();
        e.Cancel = true; // this cancels the close event.
    }

    private void btn_Cancel_Click(object sender, EventArgs e)
    {
      this.Close();
    }
  }
}
