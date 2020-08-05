#pragma once

#include <string>

class Task
{
private:
	std::string description;
	std::string status;
	int programmerId;
public:
	Task(std::string& descr) : description(descr), programmerId(0) { status = "open"; }
	Task(int id, std::string& descr, std::string& status) : description(descr), programmerId(id), status(status) {}
	Task(const Task& t);
	~Task() = default;

	std::string getDescr() const { return description; }
	std::string getStatus() const { return status; }
	int getId() const { return programmerId; }
	void start(int id, std::string& name);
	void stop();
	std::string toString() const;
};

