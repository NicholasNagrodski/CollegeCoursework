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
    public DefinitionForm(Form dictionaryForm, Headword theHeadword)
    {
      InitializeComponent();

      tb_Headword.Text = theHeadword.getHeadword;

    }

    private void btn_Cancel_Click(object sender, EventArgs e)
    {
      this.Close();
    }

    private void btn_AddIllustration_Click(object sender, EventArgs e)
    {
      new IllustrationForm().Show();
    }

    private void btn_OK_Click(object sender, EventArgs e)
    {

    }
  }
}
