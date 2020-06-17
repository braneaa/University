#pragma once

#include <QtWidgets/QWidget>
#include "ui_ProgrammerGUI.h"
#include <string>
#include "Observer.h"
#include "Controller.h"

using namespace std;
class ProgrammerGUI : public QWidget, public Observer
{
	Q_OBJECT

public:
	ProgrammerGUI(Controller* c, string& n, int i, QWidget *parent = Q_NULLPTR);
	void update() override;
private:
	Controller *control;
	string name;
	int id;
	void populate();
	Ui::ProgrammerGUIClass ui;
private slots:
	void addSlot();
	void deleteSlot();
	void showDoneSlot();
	void startSlot();
	void doneSlot();
};
