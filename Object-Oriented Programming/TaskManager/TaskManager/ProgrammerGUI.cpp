#include "ProgrammerGUI.h"
#include <iostream>
#include <QMessageBox>

ProgrammerGUI::ProgrammerGUI(Controller* c, string& n, int i, QWidget* parent)
	: QWidget(parent), control(c), name(n), id(i)
{	
	ui.setupUi(this);
	this->setWindowTitle(QString::fromStdString(name));
	populate();
	ui.doneButton->hide();
	connect(ui.addButton, SIGNAL(clicked()), this, SLOT(addSlot()));
	connect(ui.removeButton, SIGNAL(clicked()), this, SLOT(deleteSlot()));
	connect(ui.tasksList, SIGNAL(itemSelectionChanged()), this, SLOT(showDoneSlot()));
	connect(ui.startButton, SIGNAL(clicked()), this, SLOT(startSlot()));
	connect(ui.doneButton, SIGNAL(clicked()), this, SLOT(doneSlot()));
}

void ProgrammerGUI::update()
{
	populate();
}

void ProgrammerGUI::populate()
{
	ui.tasksList->clear();
	auto tasks = control->getTasks();

	for (auto task : tasks)
	{
		QString listItem = QString::fromStdString(t.toString());
		QListWidgetItem* listItemWidget = new QListWidgetItem(listItem);
		ui.listWidget->addItem(listItemWidget);
	}
	control->saveTotFile();
}

void ProgrammerGUI::addSlot()
{
	auto input = ui.descriptionInput->text().toStdString();
	if (input.size()) {
		control->addTask(input);
		ui.descriptionInput->clear();
	}
	else
	{
		QMessageBox Error;
		Error.critical(NULL, "Error 404", "Input not found!");
	}
}

void ProgrammerGUI::deleteSlot()
{
	int index = ui.tasksList->currentIndex().row();
	control->deleteTask(index);
}

void ProgrammerGUI::showDoneSlot()
{
	int index = ui.tasksList->currentIndex().row();
	if (control->getTasks()[index]->getId() == id)
		ui.doneButton->show();
	else
		ui.doneButton->hide();
}

void ProgrammerGUI::startSlot()
{
	int index = ui.tasksList->currentIndex().row();
	if (control->getTasks()[index]->getStatus() == "open") {
		control->startTask(index, id, name);
	}
	else
	{
		QMessageBox Error;
		Error.critical(NULL, "Error", "Task already taken!");
	}
}

void ProgrammerGUI::doneSlot()
{
	int index = ui.tasksList->currentIndex().row();
	control->stopTask(index);
	ui.doneButton->hide();
}
