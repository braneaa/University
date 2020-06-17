#include "PrEx.h"
PrEx::PrEx(Controller* ctrl, Ethnologist eth,QWidget *parent)
	: QMainWindow(parent), ctrl(ctrl), eth(eth), Observer()
{
	ui.setupUi(this);
	populate();
	connect(ui.addButton, SIGNAL(clicked()), this, SLOT(add()));
	connect(ui.updateButton, SIGNAL(clicked()), this, SLOT(updateB()));

	ctrl->registerObs(this);

	QWidget::setWindowTitle(eth.getName().c_str());

	
}

void PrEx::populate() {

	ui.listWidget->clear();

	for (auto bld : ctrl->getBSorted()) {
		QListWidgetItem * bs = new QListWidgetItem{ bld.toString().c_str() };
		ui.listWidget->addItem(bs);
	}
	for (int i = 0; i < ctrl->getBSorted().size(); i++) {
		if (ctrl->getBSorted()[i].getBSector() == eth.getSector())
			ui.listWidget->item(i)->setBackgroundColor(Qt::green);
	}

}

void PrEx::update() {

	ui.listWidget->clear();

	for (auto bld : ctrl->getBSorted()) {
		QListWidgetItem * bs = new QListWidgetItem{ bld.toString().c_str() };
		ui.listWidget->addItem(bs);
	}
	for (int i = 0; i < ctrl->getBSorted().size(); i++) {
		if (ctrl->getBSorted()[i].getBSector() == eth.getSector())
			ui.listWidget->item(i)->setBackgroundColor(Qt::green);
	}

}

void PrEx::add() {
	std::string id = ui.idInput->text().toStdString();
	std::string descr = ui.descrInput->text().toStdString();
	std::string year = ui.yearInput->text().toStdString();
	std::string xCoord = ui.xInput->text().toStdString();
	std::string yCoord = ui.yInput->text().toStdString();

	ctrl->add(stoi(id), descr, stoi(year), stoi(xCoord), stoi(yCoord), eth);
	ui.idInput->clear();
	ui.descrInput->clear();
	ui.yearInput->clear();
	ui.xInput->clear();
	ui.yInput->clear();

	update();
}

void PrEx::updateB() {
	std::string id = ui.idInput->text().toStdString();
	std::string descr = ui.descrInput->text().toStdString();
	std::string year = ui.yearInput->text().toStdString();
	std::string xCoord = ui.xInput->text().toStdString();
	std::string yCoord = ui.yInput->text().toStdString();
	Building b{ std::stoi(id), descr, std::stoi(year), eth.getName(), std::stoi(xCoord), std::stoi(yCoord) };
	ctrl->update(b, eth);
	ui.idInput->clear();
	ui.descrInput->clear();
	ui.yearInput->clear();
	ui.xInput->clear();
	ui.yInput->clear();

	update();


}

