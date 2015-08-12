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
    public IllustrationForm()
    {
      InitializeComponent();
    }

    private void btn_OK_Click(object sender, EventArgs e)
    {

    }

    private void btn_Cancel_Click(object sender, EventArgs e)
    {
      this.Close();
    }
  }
}
