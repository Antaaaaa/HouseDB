import java.util.Scanner;

public class UsersCLI {

    public static void main(String args[]) {
        HouseDao houseDB = new HouseDao();
        Scanner in = new Scanner(System.in);
        int command = 0;
        while (true) {
            System.out.print("1. Add new house\n2. Edit house\n3. Delete house\n4. Show all\n5. Show by parametr\n9. Exit\n>> ");
            command = in.nextInt();
            in.reset();
            switch (command) {
                case 1: {
                    Scanner input = new Scanner(System.in);
                    System.out.print("Enter a district: ");
                    String district = input.nextLine().trim();

                    System.out.print("Enter an address: ");
                    String address = input.nextLine().trim();

                    System.out.print("Enter an house area: ");
                    float area = input.nextFloat();

                    System.out.print("Enter a number of rooms: ");
                    int number = input.nextInt();

                    System.out.print("Enter a house's price: ");
                    long price = input.nextLong();

                    House house = new House(district, address, area, number, price);
                    houseDB.insertData(house);
                }
                    break;
                case 2: {
                    Scanner input = new Scanner(System.in);

                    System.out.print("Enter a district: ");
                    String district = input.nextLine().trim();

                    System.out.print("Enter an address: ");
                    String address = input.nextLine().trim();

                    System.out.print("Enter an house area: ");
                    float area = input.nextFloat();

                    System.out.print("Enter a number of rooms: ");
                    int number = input.nextInt();

                    System.out.print("Enter a house's price: ");
                    long price = input.nextLong();

                    int id = houseDB.findIdByAddress(new String[]{district,address});
                    houseDB.updateData(houseDB.findById(id), new String[]{district,address,area+"",number+"", price+""});
                }
                break;
                case 3: {
                    Scanner input = new Scanner(System.in);

                    System.out.print("Enter a district: ");
                    String district = input.nextLine().trim();

                    System.out.print("Enter an address: ");
                    String address = input.nextLine().trim();

                    int id = houseDB.findIdByAddress(new String[]{district,address});
                    houseDB.deleteData(id);
                }
                break;
                case 4: {
                    System.out.println(houseDB.getAllData());
                }
                break;
                case 5: {
                    Scanner input = new Scanner(System.in);
                    System.out.println("1. District\n2. Address\n3. Area\n4. Room Number\n5. Price");
                    int choice = input.nextInt();
                    switch (choice){
                        case 1: {
                            Scanner inputData = new Scanner(System.in);
                            System.out.print("Enter a district: ");
                            String district = inputData.nextLine().trim();
                            houseDB.getList().stream().filter(i -> i.getDistrict().equals(district)).forEach(System.out::println);
                        }
                        break;
                        case 2: {
                            Scanner inputData = new Scanner(System.in);
                            System.out.print("Enter an address: ");
                            String address = inputData.nextLine().trim();
                            houseDB.getList().stream().filter(i -> i.getAddress().equals(address)).forEach(System.out::println);
                        }
                        break;
                        case 3: {
                            Scanner inputData = new Scanner(System.in);
                            System.out.print("Enter an area: ");
                            float area = inputData.nextFloat();
                            houseDB.getList().stream().filter(i -> i.getArea() == area).forEach(System.out::println);
                        }
                        break;
                        case 4: {
                            Scanner inputData = new Scanner(System.in);
                            System.out.print("Enter a number of rooms: ");
                            int roomNumber = inputData.nextInt();
                            houseDB.getList().stream().filter(i -> i.getRoomNumber() == roomNumber).forEach(System.out::println);
                        }
                        break;
                        case 5: {
                            Scanner inputData = new Scanner(System.in);
                            System.out.print("Enter a price: ");
                            long price = inputData.nextLong();
                            houseDB.getList().stream().filter(i -> i.getPrice() == price).forEach(System.out::println);
                        }
                        break;
                        default:
                            System.out.println("Wrong Command");
                    }
                }
                break;
                case 9: System.exit(0);
                default:
                    System.out.println("Wrong Command");
            }
        }
    }
}
