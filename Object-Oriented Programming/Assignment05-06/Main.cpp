#include <iostream>
#include <stdlib.h>
#include "UI.h"
#include "Cat.h"

#include "FileRepository.h" 
#include"CSVRepo.h"
#include"HTMLRepo.h"

#include <string>



int main() {
	{
		system("color 2");
		FileRepository* repo = nullptr;
		FileRepository* adopt = nullptr;

		std::string f;
		std::cout << "Do you wish to see your list in CSV or in HTML?" << std::endl;
		std::cin >> f;
		std::string cats = "cats.txt";
		std::string catscsv = "catscsv.csv";
		if (f == "CSV") {
			repo = new CSVRepository("cats.txt");
			/*std::ofstream ofs;
			ofs.open(b, std::ofstream::out | std::ofstream::trunc);
			ofs.close();*/
			adopt = new CSVRepository(catscsv);
		}
		/*else {
			repo = new CSVRepository(cats);
			adopt = new HTMLRepository("cats.html");
		}*/
		//Cat c1{ "Siamese","Albert",6,"https://bowwowinsurance.com.au/wp-content/uploads/2018/10/siamese-cat-700x700.jpg" };
		//Cat c2{ "Sphinx","Radu",2,"https://images.fineartamerica.com/images-medium-large-5/a-hairless-sphinx-cat-wearing-pearls-james-white.jpg" };
		//Cat c3{ "Siamese","Monkey",1,"https://cdn2-www.cattime.com/assets/uploads/gallery/siamese-cats-and-kittens-pictures/siamese-cat-kitten-picture-5.jpg" };
		//Cat c4{ "Bengal", "Atac", 7,"https://assets3.thrillist.com/v1/image/2622128/size/tmg-facebook_social.jpg" };
		//Cat c5{ "Bengal", "Dumb", 2, "https://www.thesprucepets.com/thmb/iAfyrc86hbDAwzHfVxi5gab6Hf8=/1080x1080/filters:no_upscale()/28156510_214510789289294_6148465028203806720_n-5a9605c118ba010037eadf0a.jpg" };
		//repo->addCat(c1);
		//repo->addCat(c2);
		//repo->addCat(c3);
		//repo->addCat(c4);
		//repo->addCat(c5);



		Controller* ctrl = new Controller(repo, adopt);
		UI* userinterface = new UI(ctrl);
		userinterface->run();
		delete userinterface;

	}



	return 0;

}

