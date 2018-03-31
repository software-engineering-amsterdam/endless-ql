from PyQt5.QtCore import pyqtSlot
from PyQt5.QtWidgets import QDialog
from PyQt5.QtWidgets import QDialogButtonBox
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QFormLayout
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QMessageBox
from PyQt5.QtWidgets import QVBoxLayout

from gui.helper import append_file_extension


class FormWindow(QDialog):
    def __init__(self, form_model):
        super(FormWindow, self).__init__()
        self.__form_model = form_model
        self.__form_group_box = QGroupBox()

        self.setWindowTitle('Form')
        self.create_form()

        button_box = QDialogButtonBox(QDialogButtonBox.Save | QDialogButtonBox.Cancel)
        button_box.accepted.connect(self.accept)
        button_box.rejected.connect(self.reject)

        main_layout = QVBoxLayout()
        main_layout.addWidget(self.form_group_box)
        main_layout.addWidget(button_box)
        self.setLayout(main_layout)

    @property
    def form_model(self):
        return self.__form_model

    @property
    def form_group_box(self):
        return self.__form_group_box

    def create_form(self):
        layout = QFormLayout()

        for question in self.form_model.block:
            show = question.evaluate_visibility_condition(self.form_model)
            question.pyqt5_render(layout, self.form_model, show)
            question.widget.on_change(self.form_model.update_questions_on_change)

        self.form_group_box.setLayout(layout)

    @pyqtSlot(name='accept')
    def accept(self):
        file_name, _ = QFileDialog.getSaveFileName(QFileDialog(), 'Save results', self.form_model.identifier,
                                                   'JSON (*.json);;All Files (*)')

        if file_name:
            file_name = append_file_extension(file_name, 'json')
            file = open(file_name, 'w')
            file.write(self.form_model.to_json())
            file.close()
            self.close()
            QMessageBox.information(QMessageBox(), 'Submission', 'Your answers have been submitted successfully.',
                                    QMessageBox.Close, QMessageBox.Escape)
        else:
            QMessageBox.warning(QMessageBox(), 'Warning', 'Questionnaire results were not saved.',
                                QMessageBox.Close, QMessageBox.Escape)

    @pyqtSlot(name='reject')
    def reject(self):
        self.hide()
