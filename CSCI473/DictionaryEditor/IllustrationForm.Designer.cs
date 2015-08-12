namespace DictionaryEditor
{
  partial class IllustrationForm
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
        this.btn_Cancel = new System.Windows.Forms.Button();
        this.btn_OK = new System.Windows.Forms.Button();
        this.tb_Translation = new System.Windows.Forms.TextBox();
        this.tb_Illustration = new System.Windows.Forms.TextBox();
        this.tb_Definition = new System.Windows.Forms.TextBox();
        this.l_Translation = new System.Windows.Forms.Label();
        this.l_POS = new System.Windows.Forms.Label();
        this.l_Definition = new System.Windows.Forms.Label();
        this.SuspendLayout();
        // 
        // btn_Cancel
        // 
        this.btn_Cancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
        this.btn_Cancel.Location = new System.Drawing.Point(312, 238);
        this.btn_Cancel.Name = "btn_Cancel";
        this.btn_Cancel.Size = new System.Drawing.Size(82, 23);
        this.btn_Cancel.TabIndex = 20;
        this.btn_Cancel.Text = "Cancel";
        this.btn_Cancel.UseVisualStyleBackColor = true;
        this.btn_Cancel.Click += new System.EventHandler(this.btn_Cancel_Click);
        // 
        // btn_OK
        // 
        this.btn_OK.DialogResult = System.Windows.Forms.DialogResult.OK;
        this.btn_OK.Location = new System.Drawing.Point(199, 238);
        this.btn_OK.Name = "btn_OK";
        this.btn_OK.Size = new System.Drawing.Size(90, 23);
        this.btn_OK.TabIndex = 19;
        this.btn_OK.Text = "OK";
        this.btn_OK.UseVisualStyleBackColor = true;
        // 
        // tb_Translation
        // 
        this.tb_Translation.Location = new System.Drawing.Point(114, 109);
        this.tb_Translation.Multiline = true;
        this.tb_Translation.Name = "tb_Translation";
        this.tb_Translation.Size = new System.Drawing.Size(448, 116);
        this.tb_Translation.TabIndex = 18;
        // 
        // tb_Illustration
        // 
        this.tb_Illustration.Location = new System.Drawing.Point(114, 46);
        this.tb_Illustration.Multiline = true;
        this.tb_Illustration.Name = "tb_Illustration";
        this.tb_Illustration.Size = new System.Drawing.Size(448, 53);
        this.tb_Illustration.TabIndex = 16;
        // 
        // tb_Definition
        // 
        this.tb_Definition.Location = new System.Drawing.Point(114, 20);
        this.tb_Definition.Name = "tb_Definition";
        this.tb_Definition.ReadOnly = true;
        this.tb_Definition.Size = new System.Drawing.Size(448, 20);
        this.tb_Definition.TabIndex = 15;
        // 
        // l_Translation
        // 
        this.l_Translation.AutoSize = true;
        this.l_Translation.Location = new System.Drawing.Point(12, 112);
        this.l_Translation.Name = "l_Translation";
        this.l_Translation.Size = new System.Drawing.Size(59, 13);
        this.l_Translation.TabIndex = 14;
        this.l_Translation.Text = "Translation";
        // 
        // l_POS
        // 
        this.l_POS.AutoSize = true;
        this.l_POS.Location = new System.Drawing.Point(12, 53);
        this.l_POS.Name = "l_POS";
        this.l_POS.Size = new System.Drawing.Size(105, 26);
        this.l_POS.TabIndex = 12;
        this.l_POS.Text = "Illustration/Example: \r\n(Enter Only One)";
        // 
        // l_Definition
        // 
        this.l_Definition.AutoSize = true;
        this.l_Definition.Location = new System.Drawing.Point(12, 23);
        this.l_Definition.Name = "l_Definition";
        this.l_Definition.Size = new System.Drawing.Size(49, 13);
        this.l_Definition.TabIndex = 11;
        this.l_Definition.Text = "Defintion";
        // 
        // IllustrationForm
        // 
        this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
        this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        this.ClientSize = new System.Drawing.Size(592, 273);
        this.Controls.Add(this.btn_Cancel);
        this.Controls.Add(this.btn_OK);
        this.Controls.Add(this.tb_Translation);
        this.Controls.Add(this.tb_Illustration);
        this.Controls.Add(this.tb_Definition);
        this.Controls.Add(this.l_Translation);
        this.Controls.Add(this.l_POS);
        this.Controls.Add(this.l_Definition);
        this.Name = "IllustrationForm";
        this.Text = "IllustrationForm";
        this.ResumeLayout(false);
        this.PerformLayout();

    }

    #endregion

    private System.Windows.Forms.Button btn_Cancel;
    private System.Windows.Forms.Button btn_OK;
    private System.Windows.Forms.TextBox tb_Translation;
    private System.Windows.Forms.TextBox tb_Illustration;
    private System.Windows.Forms.TextBox tb_Definition;
    private System.Windows.Forms.Label l_Translation;
    private System.Windows.Forms.Label l_POS;
    private System.Windows.Forms.Label l_Definition;
  }
}