package tile;
import main.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class Floor {
	char[][] m_floor;
	String[][] m_shadowFloor;
	int m_length;
	int m_MinEnemisNbByRoom;
	int m_MaxEnemisNbByRoom;
	Random m_random;
	GamePanel gp;
	Room[][] m_room ;

	public Floor(int length,int MinEnemisNbByRoom,int MaxEnemisNbByRoom) {
		m_random = new Random();
		gp = GamePanel.getInstance();
		m_length = length;
		m_floor = createEmptyFloor('0');
		m_MinEnemisNbByRoom = MinEnemisNbByRoom;
		m_MaxEnemisNbByRoom = MaxEnemisNbByRoom;

		m_shadowFloor = createEmptyShadowFloor("-1");
		fillRoomTab();
		selection_room();
		//System.out.println("------------");
		connectFloor();
		fillRooms();

	}

	//at the end of a game, renew the componant of the floor
	public void renew(int length,int MinEnemisNbByRoom,int MaxEnemisNbByRoom){
		m_length = length;
		m_floor = createEmptyFloor('0');
		m_MinEnemisNbByRoom = MinEnemisNbByRoom;
		m_MaxEnemisNbByRoom = MaxEnemisNbByRoom;

		m_shadowFloor = createEmptyShadowFloor("-1");
		fillRoomTab();
		selection_room();
		//System.out.println("------------");
		connectFloor();
		fillRooms();

	}


	//create a empty floor with a room object for each room
	private void fillRoomTab(){

		m_room = new Room[m_length][m_length];
		for(int i = 0; i < m_length;i++){
			for(int j = 0; j < m_length;j++) {
				m_room[i][j] = new Room(gp.maxScreenCol,gp.maxScreenRow,m_random.nextInt(m_MaxEnemisNbByRoom)+m_MinEnemisNbByRoom);
			}
		}
	}

	private char[][] createEmptyFloor(char c){
		char[][] tab= new char[m_length][m_length];

		for(int i = 0; i < m_length;i++){
			for(int j = 0; j < m_length;j++) {
				tab[i][j] = c;
			}
		}

		return tab;
	}

	private String[][] createEmptyShadowFloor(String c){
		String[][] tab= new String[m_length][m_length];

		for(int i = 0; i < m_length;i++){
			for(int j = 0; j < m_length;j++) {
				tab[i][j] = c;
			}
		}

		return tab;
	}

	private void printFloor(){
		for(int i = 0; i < m_length;i++) {
			for(int j = 0; j < m_length;j++) {
				System.out.print(m_floor[i][j]);
			}
			System.out.print("\n");
		}
	}

	private void printShadowFloor(){
		for(int i = 0; i < m_length;i++) {
			for(int j = 0; j < m_length;j++) {
				System.out.print(m_shadowFloor[i][j]);
			}
			System.out.print("\n");
		}
	}

	private boolean[] room_possibility(int x,int  y) {
		// "up" "down" "right" "left"
		boolean[] selection = {true,true,true,true};

		if (0 < x && x < m_length-1 && 0 < y && y < m_length-1 && m_floor[y][x] == 'H') {

			if (m_floor[y - 1][x] == 'H') {
				selection[0] = false;
			}    
			if(m_floor[y + 1][x] == 'H') {
				selection[1] = false;
			} 
			if (m_floor[y][x + 1] == 'H') {
				selection[2] = false;
			}
			if (m_floor[y][x - 1] == 'H') {
				selection[3] = false;
			}
		}         
		else{
			for(int i = 0;i < 4;i++) {
				selection[i] = false;
			}
		}

		return selection;
	}

	private boolean gotOneTrue(boolean[] selection) {
		return selection[0] || selection[1] || selection[2] || selection[3];
	}

	private boolean creation_room(boolean[] selection,int x,int  y,char c) {
		int index = 5;

		while(gotOneTrue(selection)){
			index = m_random.nextInt(4);
			if(selection[index]) {
				break;
			}
		}

		if(index == 0) {
			m_floor[y - 1][x] = c;
			return false;
		}

		else if(index == 1){
			m_floor[y + 1][x] = c;
			return false;
		}

		else if( index == 2) {
			m_floor[y][x + 1] = c;
			return false;
		}

		else if(index == 3) {
			m_floor[y][x - 1] = c;
			return false;
		}

		else {
			return true;
		}

	}



	private void selection_room() {

		m_floor[3][3] = 'H';
		for(int i = 0;i < m_length;i++){

			int number_room = 0;
			int counter_room = 1;

			//count the number of room
			for (int y = 0; y < (int)(m_length/2);y++) {
				for (int x = 0; x < (int)(m_length/2);x++) {
					boolean[] selection = room_possibility(x, y);
					if (gotOneTrue(selection)) {
						number_room++;
					}		
				}
			}



			boolean condition = true;

			int room_alea = m_random.nextInt(number_room)+1;

			for(int y_2 = 0 ; y_2 < (int)(m_length/2) ; y_2++) {
				for(int x_2 = 0;x_2 < (int)(m_length/2); x_2++) {
					if(condition){
						boolean[] selection_2 = room_possibility(x_2, y_2);
						if(gotOneTrue(selection_2)) {
							if(counter_room == room_alea) {
								if(i < m_length-1){

									condition = creation_room(selection_2, x_2, y_2, 'H');

								}
								else condition = creation_room(selection_2, x_2, y_2, 'a');
							}
							counter_room++;
						}
					}
				}
			}
		}
		//printFloor();
	}

	private void connectFloor(){
		for (int y = 0; y<m_floor.length; y++) {
			for (int x = 0; x<m_floor[y].length; x++) {
				if (m_floor[y][x]!='0' && m_floor[y][x]!='a') {
					if(y==0) {
						if (m_floor[y][x-1]!='0' && m_floor[y][x+1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='N';
						}
						else if (m_floor[y][x-1]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='E';
						}
						else if (m_floor[y][x-1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='I';
						}
						else if (m_floor[y][x+1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='K';
						}
						else if (m_floor[y][x+1]!='0') {
							m_floor[y][x]='D';
						}
						else if (m_floor[y+1][x]!='0') {
							m_floor[y][x]='C';
						}
						else {
							m_floor[y][x]='B';
						}
					}

					else if(y==m_floor.length-1) {
						if (m_floor[y][x-1]!='0' && m_floor[y][x+1]!='0' && m_floor[y-1][x]!='0') {
							m_floor[y][x]='L';
						}
						else if (m_floor[y][x-1]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='E';
						}
						else if (m_floor[y][x-1]!='0' && m_floor[y-1][x]!='0') {
							m_floor[y][x]='G';
						}
						else if (m_floor[y][x+1]!='0' && m_floor[y-1][x]!='0') {
							m_floor[y][x]='J';
						}
						else if (m_floor[y-1][x]!='0') {
							m_floor[y][x]='A';
						}
						else if (m_floor[y][x+1]!='0') {
							m_floor[y][x]='D';
						}
						else {
							m_floor[y][x]='B';
						}

					}
					else if (x==0) {
						if (m_floor[y-1][x]!='0' && m_floor[y][x+1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='O';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='F';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='J';
						}
						else if (m_floor[y+1][x]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='K';
						}
						else if (m_floor[y-1][x]!='0') {
							m_floor[y][x]='A';
						}
						else if (m_floor[y][x+1]!='0') {
							m_floor[y][x]='D';
						}
						else {
							m_floor[y][x]='C';
						}

					}
					else if (x==m_floor[y].length-1) {
						if (m_floor[y-1][x]!='0' && m_floor[y][x-1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='M';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='F';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y][x-1]!='0') {
							m_floor[y][x]='G';
						}
						else if (m_floor[y+1][x]!='0' && m_floor[y][x-1]!='0') {
							m_floor[y][x]='I';
						}
						else if (m_floor[y-1][x]!='0') {
							m_floor[y][x]='A';
						}
						else if (m_floor[y][x-1]!='0') {
							m_floor[y][x]='B';
						}
						else {
							m_floor[y][x]='C';
						}

					}
					else {
						if (m_floor[y-1][x]!='0' && m_floor[y][x-1]!='0' && m_floor[y+1][x]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='P';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y][x-1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='M';
						}
						else if (m_floor[y][x-1]!='0' && m_floor[y][x+1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='N';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y][x+1]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='O';
						}
						else if (m_floor[y][x-1]!='0' && m_floor[y][x+1]!='0' && m_floor[y-1][x]!='0') {
							m_floor[y][x]='L';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y][x-1]!='0') {
							m_floor[y][x]='G';
						}
						else if (m_floor[y+1][x]!='0' && m_floor[y][x-1]!='0') {
							m_floor[y][x]='I';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='J';
						}
						else if (m_floor[y+1][x]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='K';
						}
						else if (m_floor[y-1][x]!='0' && m_floor[y+1][x]!='0') {
							m_floor[y][x]='F';
						}
						else if (m_floor[y][x-1]!='0' && m_floor[y][x+1]!='0') {
							m_floor[y][x]='E';
						}
						else if (m_floor[y-1][x]!='0') {
							m_floor[y][x]='A';
						}
						else if (m_floor[y][x-1]!='0') {
							m_floor[y][x]='B';
						}
						else if (m_floor[y+1][x]!='0') {
							m_floor[y][x]='C';
						}
						else {
							m_floor[y][x]='D';
						}


					}

				}
			}
		}
		//printFloor();
	}


	//return the character type of the room at the given coordinates
	public char getFileForRoom(int x, int y) {
		if(x<0 || y<0 || x>=m_floor.length-1 || y>=m_floor[0].length-1) {
			return '0';
		}
		return m_floor[y][x];
	}


	public void markRoomAsVisited(int x,int y){
		m_shadowFloor[y][x] = "0";

	}


	public boolean roomIsVisited(int x, int y) {
		return m_shadowFloor[y][x] != "-1";
	}


	public Room getRoom(int x,int y){
		return m_room[y][x];
	}

	//create a closed map from the original, to block the door entrance
	public int[][] closeMap(int[][] map){
		int[][] closedMap = new int[map.length][map[0].length];
		for(int y=0;y<closedMap.length;y++) {
			for(int x=0;x<closedMap[0].length;x++) {
				if(x == 0 || y == 0 || x == closedMap[0].length-1 || y == closedMap.length-1) {
					closedMap[y][x]=1;
				}
				else {
					closedMap[y][x]= map[y][x];
				}
			}
		}
		return closedMap;
	}


	//fill the room object at it's coordinates with the right room type and the entity
	public void fillRooms() {
		//System.out.println("Filling rooms");
		for(int y=0;y<m_floor.length;y++) {
			for(int x=0;x<m_floor[0].length;x++) {
				if(m_floor[y][x]!='0') {
					Room r = m_room[y][x];
					int[][] map = loadMap(m_floor[y][x]);
					r.setMap(map);
					r.setClosedMap(closeMap(map));


					//fills the entities in the room x,y with its max number of entities
					for(int i=0;i<r.getEnemisNumber();i++) {
						int num = m_random.nextInt(3)+1;
						m_room[y][x].addEntity(Integer.toString(num).toCharArray()[0], m_random.nextFloat()*gp.screenWidth, m_random.nextFloat()*gp.screenHeight);
					}

				}
			}
		}
	}




	//load the map from the file with the correct name
	public int[][] loadMap(char c) {
		//charger le fichier txt de la map
		int[][] map = new int[gp.maxScreenCol][gp.maxScreenRow];
		//System.out.println("Loading map : " + c);
		try {
			InputStream is;
			if(c == 'a')
				is  = getClass().getResourceAsStream("/maps/Boss/map.txt");
			else
				is = getClass().getResourceAsStream("/maps/" + c + "/map.txt");

			BufferedReader br = new BufferedReader(new InputStreamReader(is));


			int col = 0;
			int row = 0;
			// Parcourir le fichier txt pour récupérer les valeurs
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				while (col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					map[col][row] = num;
					col++;
				}
				if (col == gp.maxScreenCol) {
					col = 0;
					row ++;
				}

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return map;
	}


}
