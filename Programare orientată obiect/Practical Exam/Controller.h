#pragma once
#include "Repository.h" 
#include "Observer.h"
#include<algorithm>

class Controller: public Subject
{
private:
	Repository *repo;

public:

	Controller() {};

	Controller(Repository* repo) : repo(repo), Subject() {}

	std::vector<Building> getBuildings() { return repo->getBuildings(); }

	std::vector<Building> getBSorted() {

		//Sorts the buildings ascending, by thematic sector
		//Output: blds : vector<Buildings> , the vector is sorted

		std::vector<Building> blds = this->getBuildings();
		std::sort(blds.begin(), blds.end(), [](Building b1, Building b2) {
			return b1.getBSector() < b2.getBSector();
		});
		
		return blds;
	}

	void add(int id, std::string descr, int year, int xCoord, int yCoord, Ethnologist e) {
		//Adds an building to repository.


		Building b{ id, descr, year,e.getSector(),xCoord,yCoord };
		for (int i = 0; i < this->getBuildings().size(); i++) {
			if (b.getId() == this->getBuildings()[i].getId()) return;
		}
		if (b.getDescription() == "") return;
		if (b.getYear() > 1970 || b.getYear() < 0) return;

		repo->addBuilding(b);
		notify();
	}

	void update(Building b, Ethnologist e) {
		if (b.getBSector() != e.getSector()) return;
		repo->updateBuilding(b);
		notify();
	}

	~Controller() {};

};