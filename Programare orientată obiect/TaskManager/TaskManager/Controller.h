#pragma once
#include "Task.h"
#include "Observer.h"
#include "vector"
class Controller :
	public Subject
{
private:
	std::vector<Task*> tasks;
public:
	Controller(std::vector<Task*> t): tasks(t) {};
	~Controller() = default;
	std::vector<Task*> getTasks() { return tasks; }

	void addTask(std::string& description);
	void deleteTask(int positon);
	void startTask(int position, int id, std::string& name);
	void stopTask(int position);

	void saveTotFile();

};

