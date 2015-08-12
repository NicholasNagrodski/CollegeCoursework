namespace DictionaryEditor
{
    partial class DictionaryForm
    {
        /// <summary>
        /// This file contains all the code to control the components on the form.
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
            this.tb_HeadWord = new System.Windows.Forms.TextBox();
            this.tb_Pronunciation = new System.Windows.Forms.TextBox();
            this.btn_SpellCheck = new System.Windows.Forms.Button();
            this.btn_AddDefinition = new System.Windows.Forms.Button();
            this.cb_PartOfSpeech = new System.Windows.Forms.ComboBox();
            this.l_HeadWord = new System.Windows.Forms.Label();
            this.l_PartOfSpeech = new System.Windows.Forms.Label();
            this.l_Pronunciation = new System.Windows.Forms.Label();
            this.l_Semantics = new System.Windows.Forms.Label();
            this.l_SocialUsage = new System.Windows.Forms.Label();
            this.l_CrossReferences = new System.Windows.Forms.Label();
            this.lb_Semantics = new System.Windows.Forms.ListBox();
            this.lb_SocialUsage = new System.Windows.Forms.ListBox();
            this.tb_CrossReference = new System.Windows.Forms.TextBox();
            this.btn_AddEntry = new System.Windows.Forms.Button();
            this.btn_SearchEdit = new System.Windows.Forms.Button();
            this.btn_SeeAllWrods = new System.Windows.Forms.Button();
            this.btn_Clear = new System.Windows.Forms.Button();
            this.btn_Exit = new System.Windows.Forms.Button();
            this.l_clock = new System.Windows.Forms.Label();
            this.btn_wordsOnOff = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // tb_HeadWord
            // 
            this.tb_HeadWord.Location = new System.Drawing.Point(102, 81);
            this.tb_HeadWord.Name = "tb_HeadWord";
            this.tb_HeadWord.Size = new System.Drawing.Size(130, 20);
            this.tb_HeadWord.TabIndex = 0;
            // 
            // tb_Pronunciation
            // 
            this.tb_Pronunciation.Location = new System.Drawing.Point(102, 133);
            this.tb_Pronunciation.Name = "tb_Pronunciation";
            this.tb_Pronunciation.Size = new System.Drawing.Size(130, 20);
            this.tb_Pronunciation.TabIndex = 2;
            // 
            // btn_SpellCheck
            // 
            this.btn_SpellCheck.Location = new System.Drawing.Point(244, 81);
            this.btn_SpellCheck.Name = "btn_SpellCheck";
            this.btn_SpellCheck.Size = new System.Drawing.Size(116, 19);
            this.btn_SpellCheck.TabIndex = 3;
            this.btn_SpellCheck.Text = "Spell Check";
            this.btn_SpellCheck.UseVisualStyleBackColor = true;
            this.btn_SpellCheck.Click += new System.EventHandler(this.btn_SpellCheck_Click);
            // 
            // btn_AddDefinition
            // 
            this.btn_AddDefinition.Location = new System.Drawing.Point(244, 106);
            this.btn_AddDefinition.Name = "btn_AddDefinition";
            this.btn_AddDefinition.Size = new System.Drawing.Size(116, 19);
            this.btn_AddDefinition.TabIndex = 4;
            this.btn_AddDefinition.Text = "Add Definition";
            this.btn_AddDefinition.UseVisualStyleBackColor = true;
            this.btn_AddDefinition.Click += new System.EventHandler(this.btn_AddDefinition_Click);
            // 
            // cb_PartOfSpeech
            // 
            this.cb_PartOfSpeech.FormattingEnabled = true;
            this.cb_PartOfSpeech.Items.AddRange(new object[] {
            "adj. (Adjective)",
            "adv. (Adverb)",
            "conj. (Conjunction)",
            "ideo. (Ideom)",
            "prep. (Preposition)",
            "pro. (Pronoun)",
            "n. (Noun)",
            "v. (Verb)"});
            this.cb_PartOfSpeech.Location = new System.Drawing.Point(102, 106);
            this.cb_PartOfSpeech.Name = "cb_PartOfSpeech";
            this.cb_PartOfSpeech.Size = new System.Drawing.Size(130, 21);
            this.cb_PartOfSpeech.TabIndex = 5;
            // 
            // l_HeadWord
            // 
            this.l_HeadWord.AutoSize = true;
            this.l_HeadWord.Location = new System.Drawing.Point(34, 81);
            this.l_HeadWord.Name = "l_HeadWord";
            this.l_HeadWord.Size = new System.Drawing.Size(62, 13);
            this.l_HeadWord.TabIndex = 6;
            this.l_HeadWord.Text = "Head Word";
            // 
            // l_PartOfSpeech
            // 
            this.l_PartOfSpeech.AutoSize = true;
            this.l_PartOfSpeech.Location = new System.Drawing.Point(16, 106);
            this.l_PartOfSpeech.Name = "l_PartOfSpeech";
            this.l_PartOfSpeech.Size = new System.Drawing.Size(80, 13);
            this.l_PartOfSpeech.TabIndex = 7;
            this.l_PartOfSpeech.Text = "Part Of Speech";
            // 
            // l_Pronunciation
            // 
            this.l_Pronunciation.AutoSize = true;
            this.l_Pronunciation.Location = new System.Drawing.Point(24, 134);
            this.l_Pronunciation.Name = "l_Pronunciation";
            this.l_Pronunciation.Size = new System.Drawing.Size(72, 13);
            this.l_Pronunciation.TabIndex = 8;
            this.l_Pronunciation.Text = "Pronunciation";
            // 
            // l_Semantics
            // 
            this.l_Semantics.AutoSize = true;
            this.l_Semantics.Location = new System.Drawing.Point(15, 183);
            this.l_Semantics.Name = "l_Semantics";
            this.l_Semantics.Size = new System.Drawing.Size(81, 13);
            this.l_Semantics.TabIndex = 9;
            this.l_Semantics.Text = "Semantic Fields";
            // 
            // l_SocialUsage
            // 
            this.l_SocialUsage.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.l_SocialUsage.AutoSize = true;
            this.l_SocialUsage.Location = new System.Drawing.Point(279, 183);
            this.l_SocialUsage.Name = "l_SocialUsage";
            this.l_SocialUsage.Size = new System.Drawing.Size(70, 13);
            this.l_SocialUsage.TabIndex = 10;
            this.l_SocialUsage.Text = "Social Usage";
            // 
            // l_CrossReferences
            // 
            this.l_CrossReferences.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.l_CrossReferences.AutoSize = true;
            this.l_CrossReferences.Location = new System.Drawing.Point(34, 276);
            this.l_CrossReferences.Name = "l_CrossReferences";
            this.l_CrossReferences.Size = new System.Drawing.Size(62, 26);
            this.l_CrossReferences.TabIndex = 11;
            this.l_CrossReferences.Text = "Cross \r\nReferences";
            // 
            // lb_Semantics
            // 
            this.lb_Semantics.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lb_Semantics.FormattingEnabled = true;
            this.lb_Semantics.Location = new System.Drawing.Point(102, 180);
            this.lb_Semantics.Name = "lb_Semantics";
            this.lb_Semantics.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.lb_Semantics.Size = new System.Drawing.Size(158, 82);
            this.lb_Semantics.TabIndex = 12;
            // 
            // lb_SocialUsage
            // 
            this.lb_SocialUsage.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lb_SocialUsage.FormattingEnabled = true;
            this.lb_SocialUsage.Location = new System.Drawing.Point(355, 180);
            this.lb_SocialUsage.Name = "lb_SocialUsage";
            this.lb_SocialUsage.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.lb_SocialUsage.Size = new System.Drawing.Size(224, 82);
            this.lb_SocialUsage.TabIndex = 13;
            // 
            // tb_CrossReference
            // 
            this.tb_CrossReference.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tb_CrossReference.Location = new System.Drawing.Point(102, 273);
            this.tb_CrossReference.Multiline = true;
            this.tb_CrossReference.Name = "tb_CrossReference";
            this.tb_CrossReference.Size = new System.Drawing.Size(477, 145);
            this.tb_CrossReference.TabIndex = 14;
            // 
            // btn_AddEntry
            // 
            this.btn_AddEntry.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btn_AddEntry.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_AddEntry.Location = new System.Drawing.Point(48, 435);
            this.btn_AddEntry.Name = "btn_AddEntry";
            this.btn_AddEntry.Size = new System.Drawing.Size(90, 25);
            this.btn_AddEntry.TabIndex = 15;
            this.btn_AddEntry.Text = "Add Entry";
            this.btn_AddEntry.UseVisualStyleBackColor = true;
            this.btn_AddEntry.Click += new System.EventHandler(this.btn_AddEntry_Click);
            // 
            // btn_SearchEdit
            // 
            this.btn_SearchEdit.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btn_SearchEdit.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_SearchEdit.Location = new System.Drawing.Point(145, 435);
            this.btn_SearchEdit.Name = "btn_SearchEdit";
            this.btn_SearchEdit.Size = new System.Drawing.Size(90, 25);
            this.btn_SearchEdit.TabIndex = 16;
            this.btn_SearchEdit.Text = "Search/Edit";
            this.btn_SearchEdit.UseVisualStyleBackColor = true;
            // 
            // btn_SeeAllWrods
            // 
            this.btn_SeeAllWrods.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btn_SeeAllWrods.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_SeeAllWrods.Location = new System.Drawing.Point(341, 435);
            this.btn_SeeAllWrods.Name = "btn_SeeAllWrods";
            this.btn_SeeAllWrods.Size = new System.Drawing.Size(90, 25);
            this.btn_SeeAllWrods.TabIndex = 18;
            this.btn_SeeAllWrods.Text = "See All Words";
            this.btn_SeeAllWrods.UseVisualStyleBackColor = true;
            // 
            // btn_Clear
            // 
            this.btn_Clear.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btn_Clear.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_Clear.Location = new System.Drawing.Point(244, 435);
            this.btn_Clear.Name = "btn_Clear";
            this.btn_Clear.Size = new System.Drawing.Size(90, 25);
            this.btn_Clear.TabIndex = 17;
            this.btn_Clear.Text = "Clear";
            this.btn_Clear.UseVisualStyleBackColor = true;
            this.btn_Clear.Click += new System.EventHandler(this.btn_Clear_Click);
            // 
            // btn_Exit
            // 
            this.btn_Exit.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btn_Exit.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_Exit.Location = new System.Drawing.Point(438, 435);
            this.btn_Exit.Name = "btn_Exit";
            this.btn_Exit.Size = new System.Drawing.Size(90, 25);
            this.btn_Exit.TabIndex = 20;
            this.btn_Exit.Text = "Exit";
            this.btn_Exit.UseVisualStyleBackColor = true;
            this.btn_Exit.Click += new System.EventHandler(this.btn_Exit_Click);
            // 
            // l_clock
            // 
            this.l_clock.AutoSize = true;
            this.l_clock.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.l_clock.Font = new System.Drawing.Font("Arial", 15.75F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Italic))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.l_clock.Location = new System.Drawing.Point(403, 134);
            this.l_clock.Name = "l_clock";
            this.l_clock.Size = new System.Drawing.Size(136, 26);
            this.l_clock.TabIndex = 21;
            this.l_clock.Text = "00:00:00 AM";
            // 
            // btn_wordsOnOff
            // 
            this.btn_wordsOnOff.Location = new System.Drawing.Point(244, 134);
            this.btn_wordsOnOff.Name = "btn_wordsOnOff";
            this.btn_wordsOnOff.Size = new System.Drawing.Size(116, 19);
            this.btn_wordsOnOff.TabIndex = 23;
            this.btn_wordsOnOff.Text = "Turn Words On";
            this.btn_wordsOnOff.UseVisualStyleBackColor = true;
            this.btn_wordsOnOff.Click += new System.EventHandler(this.btn_wordsOnOff_Click);
            // 
            // DictionaryForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(592, 473);
            this.Controls.Add(this.btn_wordsOnOff);
            this.Controls.Add(this.l_clock);
            this.Controls.Add(this.btn_Exit);
            this.Controls.Add(this.btn_SeeAllWrods);
            this.Controls.Add(this.btn_Clear);
            this.Controls.Add(this.btn_SearchEdit);
            this.Controls.Add(this.btn_AddEntry);
            this.Controls.Add(this.tb_CrossReference);
            this.Controls.Add(this.lb_SocialUsage);
            this.Controls.Add(this.lb_Semantics);
            this.Controls.Add(this.l_CrossReferences);
            this.Controls.Add(this.l_SocialUsage);
            this.Controls.Add(this.l_Semantics);
            this.Controls.Add(this.l_Pronunciation);
            this.Controls.Add(this.l_PartOfSpeech);
            this.Controls.Add(this.l_HeadWord);
            this.Controls.Add(this.cb_PartOfSpeech);
            this.Controls.Add(this.btn_AddDefinition);
            this.Controls.Add(this.btn_SpellCheck);
            this.Controls.Add(this.tb_Pronunciation);
            this.Controls.Add(this.tb_HeadWord);
            this.MinimumSize = new System.Drawing.Size(600, 500);
            this.Name = "DictionaryForm";
            this.Padding = new System.Windows.Forms.Padding(10);
            this.Text = "Dictionary Editor";
            this.SizeChanged += new System.EventHandler(this.DictionaryForm_SizeChanged);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox tb_HeadWord;
        private System.Windows.Forms.TextBox tb_Pronunciation;
        private System.Windows.Forms.Button btn_SpellCheck;
        private System.Windows.Forms.Button btn_AddDefinition;
        private System.Windows.Forms.ComboBox cb_PartOfSpeech;
        private System.Windows.Forms.Label l_HeadWord;
        private System.Windows.Forms.Label l_PartOfSpeech;
        private System.Windows.Forms.Label l_Pronunciation;
        private System.Windows.Forms.Label l_Semantics;
        private System.Windows.Forms.Label l_SocialUsage;
        private System.Windows.Forms.Label l_CrossReferences;
        private System.Windows.Forms.ListBox lb_Semantics;
        private System.Windows.Forms.ListBox lb_SocialUsage;
        private System.Windows.Forms.TextBox tb_CrossReference;
        private System.Windows.Forms.Button btn_AddEntry;
        private System.Windows.Forms.Button btn_SearchEdit;
        private System.Windows.Forms.Button btn_SeeAllWrods;
        private System.Windows.Forms.Button btn_Clear;
        private System.Windows.Forms.Button btn_Exit;
        private System.Windows.Forms.Label l_clock;
        private System.Windows.Forms.Button btn_wordsOnOff;
    }
}

