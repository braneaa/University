#pragma once
#include <vector>
#include "DynamicVector.h"
#include "Cat.h"

class AdoptionList {

private:
	std::vector<Cat> cats;
	int crt;


public:
	AdoptionList();
	void add(const Cat& c);
	Cat getCrt();
	void open();
	void next();
	void isEmpty();

};