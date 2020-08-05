#include "PrEx.h"
#include <QtWidgets/QApplication>
#include <assert.h>
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);

	Repository repo{};
	Controller ctrl{ &repo };

	Ethnologist x{ "Dumi", "sector6" };

	ctrl.add(20, "b10", 1923, 7, 9, x);
	assert(ctrl.getBuildings().size()==5);

	ctrl.add(20, "b10", 1923, 7, 9, x);
	assert(ctrl.getBuildings().size() == 5);

	ctrl.add(21, "b10", 1923, 7, 9, x);
	assert(ctrl.getBuildings().size() == 6);


	std::vector<PrEx*> wind;

	for (auto i : repo.getEthnologists())
	{
		wind.push_back(new PrEx{ &ctrl, i });
	}

	for (auto w : wind) {
		w->show();
	}

	return a.exec();
}
