#include "AdoptionList.h"

AdoptionList::AdoptionList() { crt = 0; }

void AdoptionList::add(const Cat& c) {
	this->cats.push_back(c);
}

Cat AdoptionList::getCrt() {
	if (this->cats.size() == this->crt) this->crt = 0;
	return this->cats[this->crt];
}

void AdoptionList::next() {
	if (this->cats.size() == 0)
		return;
	this->crt += 1;
	this->getCrt().open();
}

void AdoptionList::isEmpty() {
	while (!(this->cats.size() == 0))
	{	
		this->cats.pop_back();
	}
}

void AdoptionList::open() {
	if (this->cats.size() == 0)
		return;
	this->crt = 0;
	this->getCrt().open();
}