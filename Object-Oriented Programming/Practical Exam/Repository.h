#pragma once
#include"Building.h"
#include "Ethnologist.h"
#include <vector>
class Repository {

private:
	void loadEthnologists();
	void loadBuilding();
	std::vector<Ethnologist> ethnologists;
	std::vector<Building> buildings;
	void saveBuildings();

public:

	Repository() {
		loadEthnologists();
		loadBuilding();
	}
	std::vector<Ethnologist> getEthnologists() { return ethnologists; }
	std::vector<Building> getBuildings() { return buildings; }

	void addBuilding(Building b) {
		buildings.push_back(b);
		saveBuildings();
	}
	void updateBuilding(Building b) {
		for (int i = 0; i < buildings.size(); i++) {
			if (buildings[i].getId() == b.getId())
			{
				buildings.erase(buildings.begin() + i);
				break;
			}
		}
		buildings.push_back(b);
		saveBuildings();
	}

	~Repository() {};


};