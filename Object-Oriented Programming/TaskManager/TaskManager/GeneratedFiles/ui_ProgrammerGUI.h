/********************************************************************************
** Form generated from reading UI file 'ProgrammerGUI.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_PROGRAMMERGUI_H
#define UI_PROGRAMMERGUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_ProgrammerGUIClass
{
public:
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QListWidget *tasksList;
    QFormLayout *formLayout;
    QLineEdit *descriptionInput;
    QLabel *label;
    QGridLayout *gridLayout;
    QPushButton *removeButton;
    QPushButton *addButton;
    QPushButton *startButton;
    QPushButton *doneButton;

    void setupUi(QWidget *ProgrammerGUIClass)
    {
        if (ProgrammerGUIClass->objectName().isEmpty())
            ProgrammerGUIClass->setObjectName(QString::fromUtf8("ProgrammerGUIClass"));
        ProgrammerGUIClass->resize(357, 394);
        verticalLayoutWidget = new QWidget(ProgrammerGUIClass);
        verticalLayoutWidget->setObjectName(QString::fromUtf8("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(20, 10, 321, 371));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        tasksList = new QListWidget(verticalLayoutWidget);
        tasksList->setObjectName(QString::fromUtf8("tasksList"));

        verticalLayout->addWidget(tasksList);

        formLayout = new QFormLayout();
        formLayout->setSpacing(6);
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        descriptionInput = new QLineEdit(verticalLayoutWidget);
        descriptionInput->setObjectName(QString::fromUtf8("descriptionInput"));

        formLayout->setWidget(0, QFormLayout::FieldRole, descriptionInput);

        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QString::fromUtf8("label"));

        formLayout->setWidget(0, QFormLayout::LabelRole, label);


        verticalLayout->addLayout(formLayout);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        removeButton = new QPushButton(verticalLayoutWidget);
        removeButton->setObjectName(QString::fromUtf8("removeButton"));

        gridLayout->addWidget(removeButton, 1, 1, 1, 1);

        addButton = new QPushButton(verticalLayoutWidget);
        addButton->setObjectName(QString::fromUtf8("addButton"));

        gridLayout->addWidget(addButton, 1, 0, 1, 1);


        verticalLayout->addLayout(gridLayout);

        startButton = new QPushButton(verticalLayoutWidget);
        startButton->setObjectName(QString::fromUtf8("startButton"));

        verticalLayout->addWidget(startButton);

        doneButton = new QPushButton(verticalLayoutWidget);
        doneButton->setObjectName(QString::fromUtf8("doneButton"));

        verticalLayout->addWidget(doneButton);


        retranslateUi(ProgrammerGUIClass);

        QMetaObject::connectSlotsByName(ProgrammerGUIClass);
    } // setupUi

    void retranslateUi(QWidget *ProgrammerGUIClass)
    {
        ProgrammerGUIClass->setWindowTitle(QApplication::translate("ProgrammerGUIClass", "ProgrammerGUI", nullptr));
        label->setText(QApplication::translate("ProgrammerGUIClass", "Description:", nullptr));
        removeButton->setText(QApplication::translate("ProgrammerGUIClass", "Remove", nullptr));
        addButton->setText(QApplication::translate("ProgrammerGUIClass", "Add", nullptr));
        startButton->setText(QApplication::translate("ProgrammerGUIClass", "Start", nullptr));
        doneButton->setText(QApplication::translate("ProgrammerGUIClass", "Done", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ProgrammerGUIClass: public Ui_ProgrammerGUIClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_PROGRAMMERGUI_H
