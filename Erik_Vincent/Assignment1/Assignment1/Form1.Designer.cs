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
            this.mainToolStrip = new System.Windows.Forms.ToolStrip();
            this.openFileButton = new System.Windows.Forms.ToolStripButton();
            this._mainPanel = new System.Windows.Forms.FlowLayoutPanel();
            this._messagePanel = new System.Windows.Forms.FlowLayoutPanel();
            this._questionFormPanel = new System.Windows.Forms.Panel();
            this.mainToolStrip.SuspendLayout();
            this._mainPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // mainToolStrip
            // 
            this.mainToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.openFileButton});
            this.mainToolStrip.Location = new System.Drawing.Point(0, 0);
            this.mainToolStrip.Name = "mainToolStrip";
            this.mainToolStrip.Size = new System.Drawing.Size(784, 25);
            this.mainToolStrip.TabIndex = 2;
            this.mainToolStrip.Text = "toolStrip1";
            // 
            // openFileButton
            // 
            this.openFileButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.openFileButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.openFileButton.Name = "openFileButton";
            this.openFileButton.Size = new System.Drawing.Size(59, 22);
            this.openFileButton.Text = "Open file";
            // 
            // _mainPanel
            // 
            this._mainPanel.AutoScroll = true;
            this._mainPanel.BackColor = System.Drawing.SystemColors.Control;
            this._mainPanel.Controls.Add(this._messagePanel);
            this._mainPanel.Controls.Add(this._questionFormPanel);
            this._mainPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this._mainPanel.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;
            this._mainPanel.Location = new System.Drawing.Point(0, 25);
            this._mainPanel.Name = "_mainPanel";
            this._mainPanel.Size = new System.Drawing.Size(784, 536);
            this._mainPanel.TabIndex = 0;
            this._mainPanel.WrapContents = false;
            // 
            // _messagePanel
            // 
            this._messagePanel.AutoSize = true;
            this._messagePanel.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this._messagePanel.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;
            this._messagePanel.Location = new System.Drawing.Point(3, 3);
            this._messagePanel.Name = "_messagePanel";
            this._messagePanel.Size = new System.Drawing.Size(0, 0);
            this._messagePanel.TabIndex = 0;
            // 
            // _questionFormPanel
            // 
            this._questionFormPanel.AutoSize = true;
            this._questionFormPanel.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this._questionFormPanel.Location = new System.Drawing.Point(3, 9);
            this._questionFormPanel.Name = "_questionFormPanel";
            this._questionFormPanel.Size = new System.Drawing.Size(0, 0);
            this._questionFormPanel.TabIndex = 1;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(784, 561);
            this.Controls.Add(this._mainPanel);
            this.Controls.Add(this.mainToolStrip);
            this.MinimumSize = new System.Drawing.Size(400, 300);
            this.Name = "Form1";
            this.Text = "Form1";
            this.mainToolStrip.ResumeLayout(false);
            this.mainToolStrip.PerformLayout();
            this._mainPanel.ResumeLayout(false);
            this._mainPanel.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.ToolStrip mainToolStrip;
        private System.Windows.Forms.ToolStripButton openFileButton;
        private System.Windows.Forms.FlowLayoutPanel _mainPanel;
        private System.Windows.Forms.FlowLayoutPanel _messagePanel;
        private System.Windows.Forms.Panel _questionFormPanel;
    }
}

