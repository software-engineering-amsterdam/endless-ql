from PyQt5.QtCore import pyqtSlot
from PyQt5.QtWidgets import QDialog
from PyQt5.QtWidgets import QDialogButtonBox
from PyQt5.QtWidgets import QFormLayout
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QMessageBox
from json import dumps


class Dialog(QDialog):
    def __init__(self, form):
        super(Dialog, self).__init__()
        self.form = form
        self.formGroupBox = QGroupBox(form.identifier)
        self.create_form(form)

        buttonBox = QDialogButtonBox(QDialogButtonBox.Ok | QDialogButtonBox.Cancel)
        buttonBox.accepted.connect(self.accept)
        buttonBox.rejected.connect(self.reject)

        mainLayout = QVBoxLayout()
        mainLayout.addWidget(self.formGroupBox)
        mainLayout.addWidget(buttonBox)
        self.setLayout(mainLayout)

        self.setWindowTitle("Form")

    def create_form(self, form):
        layout = QFormLayout()

        for question in form.block:
            show = question.evaluate_show_condition(self.form)
            question.pyqt5_render(layout, form, show)
            question.widget.onChange(form.update_show_condition_on_change)

        self.formGroupBox.setLayout(layout)

    # TODO unique file name
    @pyqtSlot()
    def accept(self):
        result = {}

        for child in self.formGroupBox.children()[1:]:
            question = self.form.find_question_of_widget(child)

            if question:
                result[question.identifier] = child.value()

        print(result)

        with open('out.json', 'w') as file:
            file.write(dumps(result))

        self.close()
        QMessageBox.information(self, 'Submission', 'Your answers have been submitted successfully.', QMessageBox.Ok, QMessageBox.Ok)

    @pyqtSlot()
    def reject(self):
        self.hide()
