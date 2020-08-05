#pragma once
#include <vector>
class Observer
{
public:
	virtual void update() = 0;
	virtual ~Observer() {}
};



class Subject
{
private:
	std::vector<Observer*> observers;

public:
	void addObserver(Observer* obs)
	{
		this->observers.push_back(obs);
	}

	void notify()
	{
		for (auto obs : this->observers)
			obs->update();
	}
};