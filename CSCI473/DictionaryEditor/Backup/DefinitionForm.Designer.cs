namespace DictionaryEditor
{
  partial class DefinitionForm
  {
    /// <summary>
    /// Required designer variable.
    /// </summary>
    private System.ComponentModel.IContainer components = null;

    /// <summary>
    /// Clean up any resources being used.
    /// </summary>
    /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
    protected override void Dispose(bool disposing)
    {
      if (disposing && (components != null))
      {
        components.Dispose();
      }
      base.Dispose(disposing);
    }

    #region Windows Form Designer generated code

    /// <summary>
    /// Required method for Designer support - do not modify
    /// the contents of this method with the code editor.
    /// </summary>
    private void InitializeComponent()
    {
      this.l_Headword = new System.Windows.Forms.Label();
      this.l_POS = new System.Windows.Forms.Label();
      this.l_ShortDefinition = new System.Windows.Forms.Label();
      this.l_Definition = new System.Windows.Forms.Label();
      this.tb_Headword = new System.Windows.Forms.TextBox();
      this.tb_ShortDef = new System.Windows.Forms.TextBox();
      this.cb_POS = new System.Windows.Forms.ComboBox();
      this.textBox2 = new System.Windows.Forms.TextBox();
      this.btn_OK = new System.Windows.Forms.Button();
      this.btn_Cancel = new System.Windows.Forms.Button();
      this.btn_AddIllustration = new System.Windows.Forms.Button();
      this.l_NumberOfIllustartions = new System.Windows.Forms.Label();
      this.tb_NumberOfIllustrations = new System.Windows.Forms.TextBox();
      this.SuspendLayout();
      // 
      // l_Headword
      // 
      this.l_Headword.AutoSize = true;
      this.l_Headword.Location = new System.Drawing.Point(12, 23);
      this.l_Headword.Name = "l_Headword";
      this.l_Headword.Size = new System.Drawing.Size(56, 13);
      this.l_Headword.TabIndex = 0;
      this.l_Headword.Text = "Headword";
      // 
      // l_POS
      // 
      this.l_POS.AutoSize = true;
      this.l_POS.Location = new System.Drawing.Point(12, 53);
      this.l_POS.Name = "l_POS";
      this.l_POS.Size = new System.Drawing.Size(80, 13);
      this.l_POS.TabIndex = 1;
      this.l_POS.Text = "Part Of Speech";
      // 
      // l_ShortDefinition
      // 
      this.l_ShortDefinition.AutoSize = true;
      this.l_ShortDefinition.Location = new System.Drawing.Point(12, 82);
      this.l_ShortDefinition.Name = "l_ShortDefinition";
      this.l_ShortDefinition.Size = new System.Drawing.Size(79, 13);
      this.l_ShortDefinition.TabIndex = 2;
      this.l_ShortDefinition.Text = "Short Definition";
      // 
      // l_Definition
      // 
      this.l_Definition.AutoSize = true;
      this.l_Definition.Location = new System.Drawing.Point(12, 112);
      this.l_Definition.Name = "l_Definition";
      this.l_Definition.Size = new System.Drawing.Size(51, 13);
      this.l_Definition.TabIndex = 3;
      this.l_Definition.Text = "Definition";
      // 
      // tb_Headword
      // 
      this.tb_Headword.Location = new System.Drawing.Point(114, 20);
      this.tb_Headword.Name = "tb_Headword";
      this.tb_Headword.ReadOnly = true;
      this.tb_Headword.Size = new System.Drawing.Size(175, 20);
      this.tb_Headword.TabIndex = 4;
      // 
      // tb_ShortDef
      // 
      this.tb_ShortDef.Location = new System.Drawing.Point(114, 79);
      this.tb_ShortDef.Name = "tb_ShortDef";
      this.tb_ShortDef.Size = new System.Drawing.Size(401, 20);
      this.tb_ShortDef.TabIndex = 6;
      // 
      // cb_POS
      // 
      this.cb_POS.FormattingEnabled = true;
      this.cb_POS.Location = new System.Drawing.Point(114, 50);
      this.cb_POS.Name = "cb_POS";
      this.cb_POS.Size = new System.Drawing.Size(175, 21);
      this.cb_POS.TabIndex = 7;
      // 
      // textBox2
      // 
      this.textBox2.Location = new System.Drawing.Point(114, 109);
      this.textBox2.Multiline = true;
      this.textBox2.Name = "textBox2";
      this.textBox2.Size = new System.Drawing.Size(401, 116);
      this.textBox2.TabIndex = 8;
      // 
      // btn_OK
      // 
      this.btn_OK.Location = new System.Drawing.Point(199, 238);
      this.btn_OK.Name = "btn_OK";
      this.btn_OK.Size = new System.Drawing.Size(90, 23);
      this.btn_OK.TabIndex = 9;
      this.btn_OK.Text = "OK";
      this.btn_OK.UseVisualStyleBackColor = true;
      this.btn_OK.Click += new System.EventHandler(this.btn_OK_Click);
      // 
      // btn_Cancel
      // 
      this.btn_Cancel.Location = new System.Drawing.Point(312, 238);
      this.btn_Cancel.Name = "btn_Cancel";
      this.btn_Cancel.Size = new System.Drawing.Size(82, 23);
      this.btn_Cancel.TabIndex = 10;
      this.btn_Cancel.Text = "Cancel";
      this.btn_Cancel.UseVisualStyleBackColor = true;
      this.btn_Cancel.Click += new System.EventHandler(this.btn_Cancel_Click);
      // 
      // btn_AddIllustration
      // 
      this.btn_AddIllustration.Location = new System.Drawing.Point(344, 48);
      this.btn_AddIllustration.Name = "btn_AddIllustration";
      this.btn_AddIllustration.Size = new System.Drawing.Size(171, 23);
      this.btn_AddIllustration.TabIndex = 11;
      this.btn_AddIllustration.Text = "Add Illustration";
      this.btn_AddIllustration.UseVisualStyleBackColor = true;
      this.btn_AddIllustration.Click += new System.EventHandler(this.btn_AddIllustration_Click);
      // 
      // l_NumberOfIllustartions
      // 
      this.l_NumberOfIllustartions.AutoSize = true;
      this.l_NumberOfIllustartions.Location = new System.Drawing.Point(341, 23);
      this.l_NumberOfIllustartions.Name = "l_NumberOfIllustartions";
      this.l_NumberOfIllustartions.Size = new System.Drawing.Size(119, 13);
      this.l_NumberOfIllustartions.TabIndex = 12;
      this.l_NumberOfIllustartions.Text = "Number Of Illustrations: ";
      // 
      // tb_NumberOfIllustrations
      // 
      this.tb_NumberOfIllustrations.Location = new System.Drawing.Point(466, 20);
      this.tb_NumberOfIllustrations.Name = "tb_NumberOfIllustrations";
      this.tb_NumberOfIllustrations.ReadOnly = true;
      this.tb_NumberOfIllustrations.Size = new System.Drawing.Size(49, 20);
      this.tb_NumberOfIllustrations.TabIndex = 13;
      this.tb_NumberOfIllustrations.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
      // 
      // DefinitionForm
      // 
      this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
      this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
      this.ClientSize = new System.Drawing.Size(592, 273);
      this.Controls.Add(this.tb_NumberOfIllustrations);
      this.Controls.Add(this.l_NumberOfIllustartions);
      this.Controls.Add(this.btn_AddIllustration);
      this.Controls.Add(this.btn_Cancel);
      this.Controls.Add(this.btn_OK);
      this.Controls.Add(this.textBox2);
      this.Controls.Add(this.cb_POS);
      this.Controls.Add(this.tb_ShortDef);
      this.Controls.Add(this.tb_Headword);
      this.Controls.Add(this.l_Definition);
      this.Controls.Add(this.l_ShortDefinition);
      this.Controls.Add(this.l_POS);
      this.Controls.Add(this.l_Headword);
      this.Name = "DefinitionForm";
      this.Text = "Definition";
      this.ResumeLayout(false);
      this.PerformLayout();

    }

    #endregion

    private System.Windows.Forms.Label l_Headword;
    private System.Windows.Forms.Label l_POS;
    private System.Windows.Forms.Label l_ShortDefinition;
    private System.Windows.Forms.Label l_Definition;
    private System.Windows.Forms.TextBox tb_Headword;
    private System.Windows.Forms.TextBox tb_ShortDef;
    private System.Windows.Forms.ComboBox cb_POS;
    private System.Windows.Forms.TextBox textBox2;
    private System.Windows.Forms.Button btn_OK;
    private System.Windows.Forms.Button btn_Cancel;
    private System.Windows.Forms.Button btn_AddIllustration;
    private System.Windows.Forms.Label l_NumberOfIllustartions;
    private System.Windows.Forms.TextBox tb_NumberOfIllustrations;
  }
}