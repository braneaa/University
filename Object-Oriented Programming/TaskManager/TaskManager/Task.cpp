#include "Task.h"

Task::Task(const Task& t)
{
	status = t.getStatus();
	description = t.getDescr();
	programmerId = t.getId();
}

void Task::start(int id, std::string& name)
{
	programmerId = id;
	status = "in progress.. " + name;
}

void Task::stop()
{
	programmerId = 0;
	status = "closed";
}

std::string Task::toString() const
{
	if (programmerId == 0)
		return " - Status: " + status + " (" + description + ")";
	else 
		return " " + std::to_string(programmerId) + " Status: " + status + " (" + description + ")";

}
