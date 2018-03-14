from PyQt5.QtCore import pyqtSlot
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QDialog
from PyQt5.QtWidgets import QDialogButtonBox
from PyQt5.QtWidgets import QFormLayout
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QMessageBox
from GUI.gui import append_file_extension
from json import dumps


class Form(QDialog):
    def __init__(self, form):
        super(Form, self).__init__()
        self.form = form
        self.form_group_box = QGroupBox(form.identifier)
        self.create_form(form)

        button_box = QDialogButtonBox(QDialogButtonBox.Save | QDialogButtonBox.Cancel)
        button_box.accepted.connect(self.accept)
        button_box.rejected.connect(self.reject)

        main_layout = QVBoxLayout()
        main_layout.addWidget(self.form_group_box)
        main_layout.addWidget(button_box)
        self.setLayout(main_layout)

        self.setWindowTitle('Form')

    def create_form(self, form):
        layout = QFormLayout()

        for question in form.block:
            show = question.evaluate_show_condition(self.form)
            question.pyqt5_render(layout, form, show)
            question.widget.onChange(form.update_show_condition_on_change)

        self.form_group_box.setLayout(layout)

    @pyqtSlot()
    def accept(self):
        result = {}

        for child in self.form_group_box.children():
            question = self.form.find_question_of_widget(child)

            if question:
                result[question.identifier] = child.value()

        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file_name, _ = QFileDialog.getSaveFileName(self, 'Save results', self.form.identifier, 'JSON (*.json);;All Files (*)', options=options)

        if file_name:
            file_name = append_file_extension(file_name, 'json')
            file = open(file_name, 'w')
            file.write(dumps(result))
            file.close()
            self.close()
            QMessageBox.information(self, 'Submission', 'Your answers have been submitted successfully.', QMessageBox.Ok, QMessageBox.Ok)
        else:
            QMessageBox.warning(self, 'Warning', 'Questionnaire results were not saved.', QMessageBox.Ok, QMessageBox.Ok)

    @pyqtSlot()
    def reject(self):
        self.hide()
