#include <iostream>
#include <vector>
#include <fstream>

using std::cout, std::string, std::endl, std::vector, std::ofstream; // don't use using namespace std VERY bad practice

class Star
{
    private:
        string name;
        int x;
        int y;
        int z;
        int weight;
        int profit;
        // Star* pConnectedStar[3];
        vector<Star> connectedStar; // some stars need to be connected to more than 3 stars

    public:
        static int edgeCount;
        static const int TOTAL_EDGE = 54;

        void setName(string name)
        {
            this->name = name;
        }

        void setX(int x)
        {
            this->x = x;
        }

        void setY(int y)
        {
            this->y = y;
        }

        void setZ(int z)
        {
            this->z = z;
        }

        void setWeight(int weight)
        {
            this->weight = weight;
        }

        void setProfit(int profit)
        {
            this->profit = profit;
        }

        string getName()
        {
            return name;
        }

        int getX()
        {
            return x;
        }

        int getY()
        {
            return y;
        }

        int getZ()
        {
            return z;
        }

        int getWeight()
        {
            return weight;
        }

        int getProfit()
        {
            return profit;
        }

        vector<Star> getConnectedStar() {
            return connectedStar;
        }

        void setConnectedStar(Star star) {
            this->connectedStar.push_back(star);
        }

};

int Star::edgeCount; //for testing only 

void cyclicStarConnect(Star (&stars)[]);
void setAllStarProperties (Star (&stars)[]);

int main()
{   
    Star stars[20];
    

    for (int i = 0; i < 20; i++)
    {
        stars[i] = Star();
        stars[i].setX(i);
    }

    setAllStarProperties(stars);
    cyclicStarConnect(stars);

    // cout << "\nS1: " << stars[0].getX();
    // cout << "\nS2: " << stars[1].getX();

    cout << "\n" << stars[0].getConnectedStar().size();
    cout << "\n" << Star::edgeCount;

    return 0;
}

void cyclicStarConnect(Star (&stars)[]) {

    for (int i = 0; i < 20; i++)
    {
        // stars[i].setConnectedStar(stars[i++]);
        // stars[i].setX(i);

        if (i == 19) {

            stars[i].setConnectedStar(stars[0]);
            Star::edgeCount++;
        }
        else {

            stars[i].setConnectedStar(stars[i+1]);
            Star::edgeCount++;
        }
        
    }
}

void setAllStarProperties (Star (&stars)[]) {

    ofstream file;

    long long seedRef = 1221301874LL + 1211102289 + 1211104274;

    srand(seedRef);


    for (int i = 0; i < 20; i++)
    {
        stars[i].setName("A");
        stars[i].setX(rand());
        stars[i].setY(rand());
        stars[i].setZ(rand());
        stars[i].setWeight(rand());
        stars[i].setProfit(rand());
    }

    file.open("Stars.csv");

    file << "Name, " << "x, " << "y, " << "z, " << "weight, " << "profit" << endl;

    for (int i = 0; i < 20; i++)
    {
        file << stars[i].getName() << ", ";
        file << stars[i].getX() << ", ";
        file << stars[i].getY() << ", ";
        file << stars[i].getZ() << ", ";
        file << stars[i].getWeight() << ", ";
        file << stars[i].getProfit() << endl;
    }

    file.close();
}

/*
    1. Connecting stars left rn only 1 star connected to 2 star only, cyclic graph 20 Edges used up.
    2. Update if condition to make sure connectedStar included both front & back star that it is connected to
    2. Naming of the stars wrong
*/