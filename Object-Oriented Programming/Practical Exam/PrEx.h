#pragma once

#include <QtWidgets/QMainWindow>
#include "Controller.h"
#include <QtWidgets/QWidget>
#include "ui_PrEx.h"

class PrEx : public QMainWindow, public Observer
{
	Q_OBJECT

public:
	PrEx(Controller* ctrl, Ethnologist eth ,QWidget *parent = Q_NULLPTR);

private:
	Ui::PrExClass ui;
	Controller* ctrl;
	Ethnologist eth;
	void populate();
	void update() override;

private slots:

	void add();
	void updateB();

};
