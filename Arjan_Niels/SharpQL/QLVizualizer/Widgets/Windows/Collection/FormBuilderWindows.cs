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

        protected override Control Create(Dictionary<IWidgetBuilder, Control> children)
        {
            Control result = base.Create(children);
            List<PageManager> pages = new List<PageManager>();

            foreach (IWidgetBuilder child in children.Keys)
                if (child.GetElementManager() as PageManager != null)
                    pages.Add(child.GetElementManager() as PageManager);

            if (pages.Count > 0)
            {

                // TODO EXRACT
                Control[] childControls = new Control[result.Controls.Count];
                result.Controls.CopyTo(childControls, 0);

                result.Controls.Clear();
                result.Controls.Add(CreatePageBrowser(pages));
                result.Controls.AddRange(childControls);

                ActivatePage(0, pages);
            }
            return result;
        }

        private Control CreatePageBrowser(List<PageManager> pageManagers)
        {
            ComboBox pagesHolder = new ComboBox();
            pagesHolder.Items.AddRange(pageManagers.Select(pageManager => pageManager.Text).ToArray());
            pagesHolder.SelectedIndexChanged += (object sender, EventArgs eventArgs) => ActivatePage(pagesHolder.SelectedIndex, pageManagers);
            return _styler.StyleElement(pagesHolder);
        }

        private void ActivatePage(int targetIndex, List<PageManager> pageManagers)
        {
            for (int i = 0; i < pageManagers.Count; i++)
                pageManagers[i].SetActive(i == targetIndex);
        }
    }
}
