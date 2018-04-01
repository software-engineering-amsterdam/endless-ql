namespace Assignment1
{
    partial class Form1
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.mainToolStrip = new System.Windows.Forms.ToolStrip();
            this.openFile = new System.Windows.Forms.ToolStripButton();
            this.exportAnswers = new System.Windows.Forms.ToolStripButton();
            this._mainPanel = new System.Windows.Forms.FlowLayoutPanel();
            this._messagePanel = new System.Windows.Forms.FlowLayoutPanel();
            this._questionFormPanel = new System.Windows.Forms.Panel();
            this.mainToolStrip.SuspendLayout();
            this._mainPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // mainToolStrip
            // 
            this.mainToolStrip.GripStyle = System.Windows.Forms.ToolStripGripStyle.Hidden;
            this.mainToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.openFile,
            this.exportAnswers});
            resources.ApplyResources(this.mainToolStrip, "mainToolStrip");
            this.mainToolStrip.Name = "mainToolStrip";
            // 
            // openFile
            // 
            this.openFile.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            resources.ApplyResources(this.openFile, "openFile");
            this.openFile.Name = "openFile";
            // 
            // exportAnswers
            // 
            this.exportAnswers.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            resources.ApplyResources(this.exportAnswers, "exportAnswers");
            this.exportAnswers.Name = "exportAnswers";
            // 
            // _mainPanel
            // 
            resources.ApplyResources(this._mainPanel, "_mainPanel");
            this._mainPanel.BackColor = System.Drawing.SystemColors.Control;
            this._mainPanel.Controls.Add(this._messagePanel);
            this._mainPanel.Controls.Add(this._questionFormPanel);
            this._mainPanel.Name = "_mainPanel";
            // 
            // _messagePanel
            // 
            resources.ApplyResources(this._messagePanel, "_messagePanel");
            this._messagePanel.Name = "_messagePanel";
            // 
            // _questionFormPanel
            // 
            resources.ApplyResources(this._questionFormPanel, "_questionFormPanel");
            this._questionFormPanel.Name = "_questionFormPanel";
            // 
            // Form1
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this._mainPanel);
            this.Controls.Add(this.mainToolStrip);
            this.Name = "Form1";
            this.mainToolStrip.ResumeLayout(false);
            this.mainToolStrip.PerformLayout();
            this._mainPanel.ResumeLayout(false);
            this._mainPanel.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.ToolStrip mainToolStrip;
        private System.Windows.Forms.ToolStripButton openFile;
        private System.Windows.Forms.FlowLayoutPanel _mainPanel;
        private System.Windows.Forms.FlowLayoutPanel _messagePanel;
        private System.Windows.Forms.Panel _questionFormPanel;
        private System.Windows.Forms.ToolStripButton exportAnswers;
    }
}

