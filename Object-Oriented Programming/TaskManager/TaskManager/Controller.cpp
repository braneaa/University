#include "Controller.h"
#include <iostream>
#include <fstream>
void Controller::addTask(std::string& description)
{
	tasks.push_back(new Task{description});
	
	notify();
}

void Controller::deleteTask(int positon)
{
	
	for (int i = positon; i < tasks.size() - 1; i++)
		tasks[i] = tasks[i + 1];

	tasks.pop_back();
	
	notify();
}

void Controller::startTask(int position, int id, std::string& name)
{
	tasks[position]->start(id, name);
	
	notify();
}

void Controller::stopTask(int position)
{
	tasks[position]->stop();

	notify();
}

void Controller::saveTotFile()
{
	std::ofstream output;
	output.open("tasks.txt", std::ios::out);
	for(int i=0; i < tasks.size(); i++)
	{
		std::string line;
		line = std::to_string(tasks[i]->getId()) + "," + tasks[i]->getDescr() + "," + tasks[i]->getStatus() + "\n";
		output << line;
	}
	output.close();
}

