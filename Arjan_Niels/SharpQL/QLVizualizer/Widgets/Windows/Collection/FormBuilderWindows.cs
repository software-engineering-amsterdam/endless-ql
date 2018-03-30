using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public class FormBuilderWindows : WidgetCollectionBuilderWindows<FormManager>
    {
        public FormBuilderWindows(FormManager elementManagerCollection) : base(elementManagerCollection)
        {
        }

        protected override string GetTitleText()
        {
            return string.Format("Form: {0}", _elementManagerCollection.Text);
        }

        protected override Control Create(Dictionary<IWidgetBuilder<Control>, Control> children)
        {
            Control result = base.Create(children);
            List<PageManager> pages = new List<PageManager>();

            foreach(IWidgetBuilder<Control> child in children.Keys)
                if (child.GetElementManager() as PageManager != null)
                    pages.Add(child.GetElementManager() as PageManager);

            Control[] childControls = new Control[result.Controls.Count];
            result.Controls.CopyTo(childControls, 0);

            result.Controls.Clear();
            result.Controls.Add(CreatePageBrowser(pages));
            result.Controls.AddRange(childControls);
            return result;
        }

        private Control CreatePageBrowser(List<PageManager> pageManagers)
        {
            ComboBox pagesHolder = new ComboBox();
            pagesHolder.Items.AddRange(pageManagers.Select(pageManager => pageManager.Text).ToArray());
            pagesHolder.SelectedIndexChanged += delegate (object sender, EventArgs eventArgs)
            {
                foreach (PageManager pageManager in pageManagers)
                    pageManager.SetActive(false);
                pageManagers[pagesHolder.SelectedIndex].SetActive(true);
            };
            
            return _styler.StyleElement(pagesHolder);
        }

        //private void ActivatePage(PageManager pageManager, List<PageMan)
        //{

// }
/*        private void ActivatePage(int targetIndex, List<PageManager> pageManagers)
 {
     for (int i = 0; i < pageManagers.Count; i++)
         pageManagers[i].SetActive(i == targetIndex);
 }*/
}
}
