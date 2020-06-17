#include "Repository.h"

void Repository::loadBuilding() {

	std::ifstream f("buildings.txt");
	Building bu{};
	while (f >> bu)
		buildings.push_back(bu);
	f.close();

}

void Repository::loadEthnologists() {

	std::ifstream f("ethn.txt");
	Ethnologist eth{};
	while (f >> eth)
		ethnologists.push_back(eth);
	f.close();

}

void Repository::saveBuildings() {
	std::ofstream g("buildings.txt");
	for (auto b : buildings)
		g << b.toString();
	g.close();
}