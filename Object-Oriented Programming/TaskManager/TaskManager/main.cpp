#include "ProgrammerGUI.h"
#include <QtWidgets/QApplication>
#include <iostream>
#include <fstream>
#include <sstream>
#include <QMessageBox>

std::vector<std::string> tokenize(std::string input, char delimiter)
{
    std::stringstream stream(input);

    std::vector<std::string> result;
    std::string token;

    while (getline(stream, token, delimiter))
        result.push_back(token);

    return result;
}

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	
	vector<ProgrammerGUI*> programmers;
	vector<Task*> tasks;
	ifstream userInput, taskInput;
	string line;
	
	taskInput.open("tasks.txt", ios::in);


	while (getline(taskInput, line))
	{
		auto newItem = tokenize(line, ',');
		tasks.push_back(new Task{ stoi(newItem[0]), newItem[1], newItem[2] });
	}



	taskInput.close();
	userInput.open("users.txt", ios::in);
	
	Controller* control = new Controller{tasks};
	while (userInput>>line)
	{
		
		auto newItem = tokenize(line, ',');
		programmers.push_back(new ProgrammerGUI{control, newItem[0], stoi(newItem[1].c_str(),NULL, 10)});
	}
	userInput.close();


	for (int i = 0; i < programmers.size(); i++) {
		control->addObserver(programmers[i]);
		programmers[i]->show();
	}

	
	return a.exec();
	
}
