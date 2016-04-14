package mobile.patting.Utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by goodworklabs on 30/03/2016.
 */
public class Constants {
    public static String BASE_URL = "https://patting.herokuapp.com/";
   public static String[] state_city = { };
    LatLng p1 = null;

    public String[] getBirdBreeds() {
        String[] bird = {"African Grey", "Amazon",
                "Brotogeris", "Budgie/Budgerigar", "Button Quail", "Caique",
                "Canary", "Chicken", "Cockatiel", "Cockatoo", "Conure", "Dove",
                "Duck", "Eclectus", "Emu", "Finch", "Goose", "Guinea Fowl",
                "Kakariki", "Lory/Lorikeet", "Lovebird", "Macaw", "Ostrich",
                "Parakeet", "Parrot", "Parrotlet", "Peacock/Pea fowl", "Pheasant",
                "Piegeon", "Pionus", "Poicephalus/Senegal", "Quail",
                "Quaker Parakeet", "Rhea", "RIngneck/Psittacula", "Rosella",
                "Swan", "Toucan", "Turkey"};
        return bird;
    }

    public String[] getCatBreeds() {
        String[] cats = {"Abyssinian", "American Curl",
                "American Shorthair", "American Wirehair", "Applehead Siamese",
                "Balinese", "Bengal", "Birman", "Bobtail", "Bombay",
                "Brirish Shorthair", "Burmese", "Burmilla", "Calico",
                "Canadaian Hairless", "Chartreux", "Chausie", "Chinchilla",
                "Cornish Rex", "Cymric", "Dilute Tortoiseshell",
                "Domestic Long Hair,Domestic Longe Hair -brown",
                "Domestic Long Hair -buff", "Domestic Long Hair -buff and white",
                "Domestic Long Hair -gray and white", "Domestic Long Hair -orange",
                "Domestic Long Hair -orange and white", "Domestic Long Hair-black",
                "Domestic Long Hair-black and white", "Domestic Long Hair-gray",
                "Domestic Long Hair-white", "Domestic Medium Hair",
                "Domestic Medium Hair - brown", "Domestic Medium Hair - buff",
                "Domestic Medium Hair - buff and white",
                "Domestic Medium Hair -gray and white",
                "Domestic Medium Hair -orange and white",
                "Domestic Medium Hair -orange", "Domestic Medium Hair -black",
                "Domestic Medium Hair -black and white",
                "Domestic Medium Hair-gray", "Domestic Medium Hair-orange",
                "Domestic Medium Hair-white", "Domestic Short Hair",
                "Domestic Short Hair", "Domestic Short Hair-brown",
                "Domestic Short Hair-buff", "Domestic Short Hair- buff and white",
                "Domestic Short Hair- orange and white",
                "Domestic Short Hair- gray and white", "Domestic Short Hair-black",
                "Domestic Short Hair-black and white", "Domestic Short Hair -gray",
                "Domestic Short Hair -mitted", "Domestic Short Hair -orange",
                "Domestic Short Hair -white", "Egyptian Mau", "Exotic Shorthair",
                "Extra-Toes Cat", "Havana", "Himalayan", "Japanese Bobtail",
                "Javanese", "Korat", "LaPerm", "Maine Coon", "Manx", "Munchkin",
                "Nebelung", "Norwegian Forest Cat", "Ocicat", "Ocicat",
                "Oriental Long Hair", "Oriental Short Hair", "Oriental Tabby",
                "Persian", "Pixie-Bob", "Ragamuffin", "Ragdoll", "Russian Blue",
                "Scottish Fold", "Selkirk Rex", "Siamese", "Siberian", "Silver",
                "Singapura", "Snowshoe", "Somali", "Sphynx", "Tabby",
                "Tabby - black", "Tabby - Brown", "Tabby -buff", "Tabby - Grey",
                "Tabby - Orange", "Tabby -White", "Tiger", "Tonkinese", "Torbie",
                "Tortoiseshell", "Turkish Angora", "Turkish Van", "Tuxedo"};
        return cats;
    }

    public String[] getDogBreeds() {

        String[] dogs = {"Affenpinscher", "Afghan Hound",
                "Airedale Terrier", "Akbash", "Akita", "Alaskan Makamute",
                "American Bulldog", "American Eskimo Dog",
                "American Hairless Terrier", "American Staffordshire Terrier",
                "American Water Spaniel", "Anatolian Shepherd",
                "Appenzell Mountain Dog", "Australian Kelpie",
                "Australian Shepherd", "Australian Terrier", "Basenji",
                "Basset Hound", "Beagle", "Bearded Collie", "Beauceron",
                "Bedlington Terrier", "Belgian Shepherd Dog Sheepdog",
                "Belgian Shepherd Laekenois", "Belgian Shepherd Malinois",
                "Belgian Shepherd Tervuren", "Bernese Mountain Dog",
                "Bichon Frise", "Black and Tan Coonhound",
                "Black Labrador Retriever", "Black Mouth Cur",
                "Black Russian Terrier", "Bloodhound", "Blue Lacy",
                "Bluetick Coonhound", "Boerboel", "Bolognese", "Border Collie",
                "Border Terrier ", "Borzoi", "Boston Terrier",
                "Bouvier des Flanders", "Boxer", "Boykin Spaniel", "Briard",
                "Brittany Spaniel", "Brussesls Griffon", "Brussels Griffon",
                "Bull Terrier", "Bullmastiff", "Cairn Terrier", "Canaan Dog",
                "Cane Corso Mastiff", "Carolina Dog", "Catahoula Leopard Dog",
                "Cattle Dog", "Caucasian Sheepdog (Caucasian Ovtcharka)",
                "Cavalier King Charles Spaniel", "Chesapeake Bay Retriever",
                "Chihuahua", "Chinese Crested Dog", "Chinese Foo Dog", "Chinook",
                "chocolate Labrador Retriever", "Chow Chow", "Cirneco dell'Etna",
                "Clumber Spaniel", "Cockapoo", "Cocker Spaniel", "Collie",
                "Coonhound", "Corgi", "Coton de Tulear", "Curley-Coated retiever",
                "Dachshund", "Dalmatian", "Dandi Dinmont Terrier",
                "Doberman Pinscher", "Dogo Argentino", "Dogue de Bordeaux",
                "Dutch Shepherd", "English Bulldog", "English Cocker Spaniel",
                "Englsih Coonhound", "Englsih Pointer", "English Setter",
                "English Shepherd", "English Spinger Spaniel",
                "English Toy Spaniel", "Entlebucher", "Eskimo Dog", "Feist",
                "Field Spaneil", "Fila Braseleiro", "Finnish Lapphund",
                "Finnish Spitz", "Flat-coated Retriever", "Fox Terrier",
                "Foxhound", "French Bulldog", "Galgo Spanish Greyhound",
                "German Shepherd Dog", "German Shorthaired Pointer",
                "German Spitz", "German Wirehaired Pointer", "Giant Schnauzer",
                "Glen of Imaal Terrier", "Golden Retriever", "Gordon Setter",
                "Great Dane", "Great Pyrenees", "Greater Swiss Mountain Dog",
                "Greyhound", "Harrier", "Havanese", "Hound", "Hovawart", "Husky",
                "Ibizan Hound", "Illyrian Sheepdog", "Irish Setter",
                "Irish Terrier", "Irish Water Spaniel", "Irish Wolfhound",
                "Italian Greyhound", "Italian Spinone", "Jack Russell Terrier",
                "Jack Russell Terrier(Parson Russell Terrier)", "Japanese Chin",
                "Jindo", "Kali Dog", "Karelian Bear Dog", "Keeshond",
                "Kerry Blue Terrier", "Kishu", "Klee Kai", "Komondor", "Kuvasz",
                "Kyi Leo", "Labrador Retriever", "Lakeland Terrier",
                "Lancashire Heeler", "Leonberger", "Lhasa Apso", "Lowchen",
                "Maltese", "Manchester Terrier", "Maremma Sheepdog", "Mastiff",
                "McNab", "Miniature Pinscher", "Mountain Cur", "Mountain Dog",
                "Munsteriander", "Neapolitan Mastiff", "New Guinea Singing Dog",
                "Newfoundland Dog", "Norfolk Terrier", "Norwegian Buhund",
                "Norwegian Elkhound", "Norwegian Lundehund", "Norwich Terrier",
                "Nova Scotia Duck-Tolling Retriever", "Old English Sheepdog",
                "Otterhound", "Papillon", "Patterdale Terrier(Fell Terrier)",
                "Pekingese", "Peruvian Inca Orchid",
                "Petit Basser Griffon Vendeen", "Pharaoh Hound",
                "Pit Bull Terrier", "Plott Hound", "Podengo Portugueso", "Pointer",
                "Polish Lowland Sheepdog", "Pomeranian", "Poodle",
                "Portuguese Water Dog", "Presa Canario", "Pug", "Puli", "Pumi",
                "Rat Terrier", "Redbone Coonhound", "Retriever",
                "Rhodesian Ridgeback", "Rottweiler", "Saint Bernard St.Bernard",
                "Saluki", "Samoyed", "Sarplaninac", "Schipperke", "Schnauzer",
                "Scottish Deerhound", "Scottish Terrier Scottie",
                "Sealyham Terrier", "Setter", "Shar Pei", "Sheep Dog", "Shepherd",
                "Shetland Sheepdog Sheltie", "Shiba Inu", "Shih Tzu",
                "Siberian Husky", "Silky Terrier", "Skye Terrier", "Sloughi",
                "Smooth Fox Terrier", "South Russian Ovtcharka", "Spaneil",
                "Spitz", "Staffordshire Bull Terrier", "Standard Poodle",
                "Sussex Spaniel", "Swedish Vallhund", "Terrier", "Thai Ridgeback",
                "Tibetan Mastiff", "Tibetan Spaniel", "Tibetan Terrier",
                "Tosa Inu", "Toy Fox Terrier", "Treeing Walker Coonhound",
                "Vizsla", "Weimaraner", "Welsh Corgi", "Welsh Springer Spaniel",
                "Welsh Terrier", "West Highland White Terrier Westie",
                "Wheaten Terrier", "Whippet", "White German Shepherd",
                "Wire Fox Terrier", "Wire-haired Pointing Griffon",
                "Wirehaired Terrier", "Xoloitzcuintle(Mexican Hairless)",
                "Yellow Labrator Retriever", "Yorkshire Torrier Yorkie"};
        return dogs;
    }

    public String[] getState() {
        String[] states = {"Andhra Pradesh",
                "Andaman and Nicobar Islands", "Arunachal Pradesh", "Assam",
                "Bihar", "Chandigarh", "Chhattisgarh", "Dadra and Nagar Haveli",
                "Daman and Diu", "Delhi", "Goa", "Gujarat", "Haryana",
                "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka",
                "Kerala", "Lakshadweep", "Madhya Pradesh", "Maharashtra",
                "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Orissa",
                "Pondicherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu",
                "Tripura", "Uttaranchal", "Uttar Pradesh", "West Bengal"};
        return states;
    }

    public String[] getAndhraPradeshCities() {
        String[] andhraPradeshCities = {"Adilabad", "Alwal",
                "Amalapuram", "Anantapur", "Bhimavaram", "Chittoor",
                "Cuddapah,Eluru", "Gaddiannaram", "Gajuwaka", "Guntur", "Kakinada",
                "Karimnagar", "Khamman", "Kukatpalle", "Kurnool",
                "Lalbahadur Nagar", "Machilipatnam", " Madanapalle",
                " Mahbubnagar", "  Malkajgiri", " Nalgonda", " Nandyal", "Nellore",
                "Nizamabad", "Ongole", "Qutubullapur", "Rajahmundry",
                "Rajendranagar", "Secunderabad", "Serilingampalle", "Srikakulam",
                "Tanuku", "Tenali", "Tirupati", "Uppal Kalan", "Vijayawada",
                "Vijayawada", "Visakhpatnam", "Vizianagaram", "Warangal",
                "Yemmiganur"};
        return andhraPradeshCities;
    }

    public String[] getAndamanandNicobarIslandsCities() {
        String[] Andaman_and_Nicobar_Islands = {"Port Blair"};
        return Andaman_and_Nicobar_Islands;
    }

    public String[] getArunachalPradeshCities() {
        String[] Arunachal_Pradesh = { "Itanagar"};
        return Arunachal_Pradesh;
    }

    public String[] getAssamCities() {
        String[] Assam = { "Bongaigaon", "Dhubri", "Dibrugarh",
                "Diphur", "Guwahati", "Jorhat", "Karimganj", "Lumding", "Nagaon",
                "North Lakhimpur", "North Lakhimpur", "Silchar", "Tezpur",
                "Tinsukia"};
        return Assam;
    }

    public String[] getBiharCities() {
        String[] Bihar = { "Ara", "Araria", "Aurangabad", "Bagaha",
                "Begusarai", "Bettiah", "Bhagalpur", "Bihar", "Buxar", "Chapra",
                "Darbhanga", "Dehri", "Gaya", "Gopalganj", "Hajipur", "Jamalpur",
                "Jamui", "Jehanabad", "Katihar", "Kishanganj", "Lakhisarai",
                "Madhubani", "Motihari", "Munger", "Muzaffarpur", "Nawada",
                "Phulwari Sharif", "Purnia", "Saharsa", "Samastipur", "Sasaram",
                "Sitamarhi", "Siwan", "Supaul"};
        return Bihar;
    }

    public String[] getChandigarhCities() {
        String[] Chandigarh = {"Chandigarh"};
        return Chandigarh;
    }

    public String[] getChhattisgarhCities() {
        String[] Chhattisgarh = { "Ambikapur", "Bhatapara",
                "Bhilai", "Bhilai Charoda", "Bilaspur", "Chirmiri",
                "Dalli-Rajhara", "Dhamtari", "Durg-Bhilainagar", "Jagdalpur",
                "Korba", "Raigarh", "Raipur", "Rajnandgaon"};
        return Chhattisgarh;
    }

    public String[] getDadracities() {
        String[] Dadra = { "Silvassa"};
        return Dadra;
    }

    public String[] getDamancities() {
        String[] Daman = { "Daman", "Diu"};
        return Daman;
    }

    public String[] getDelhi() {
        String[] Delhi = {"Delhi"};
        return Delhi;
    }

    public String[] getGoa() {
        String[] Goa = { "Madgoan", "Marmugao"};
        return Goa;
    }

    public String[] getGujaratCities() {
        String[] Gujarat = { "Amreli", "Anand", "Anjar",
                "Anklesvar", "Bardoli", "Bharuch", "Bhavnagar", "Bhuj", "Bilimora",
                "Chandkheda", "Dahod", "Disa", "Gandhidham", "Gandhinagar",
                "Ghatlodiya", "Godhra", "Gondal", "Himatnagar", "Jamnagar",
                "Jetpur", "Junagadh", "Kadi", "Kalol", "Mahesana", "Mandvi",
                "Modasa"};
        return Gujarat;
    }

    public String[] getHaryanaCities() {
        String[] Haryana = { "Ambala", "Ambala Cantonment",
                "Ambala Sadar", "Bahadurgarh", "Bhiwani",
                "Faridabad (New Township)", "Fatehabad", "Gurgaon", "Hansi",
                "Hissar", "Jagadhri", "Jind", "Kaithal", "Karnal", "Kurukshetra",
                "Mandi Dabwali", "Narnaul", "Narwana", "Palwal", "Panchkula",
                "Panipat", "Rewari", "Rohtak", "Sirsa", "Sonepat", "Thanesar",
                "Tohana", "Yamuna Nagar"};
        return Haryana;
    }

    public String[] getHimachalCities() {
        String[] Himachal = { "Mandi", "Shimla", "Solan"};
        return Himachal;
    }

    public String[] getJammuCities() {
        String[] Jammu = { "Anantnag", "Baramula", "Jammu",
                "Kathua", "Sopore", "Srinagar", "Udhampur"};
        return Jammu;
    }

    public String[] getJharkhandCities() {
        String[] Jharkhand = { "Adityapur", "Bagbera", "Bhuli",
                "Bokaro Steel City", "Chaibasa", "Chakradharpur", "Chas",
                "Chirkunda", "Daltonganj", "Deoghar", "Dhanbad", "Giridih",
                "Hazaribagh", "Jamshedpur", "Jharia", "Jumri Tilaiya", "Katras",
                "Mango", "Phusro", "Ramgarh", "Ranchi", "Sahibganj"};
        return Jharkhand;
    }

    public String[] getKarnatakaCities() {
        String[] Karnataka = { "Bagalkot", "Belgaum", "Bellary",
                "Bhadravati", "Bidar", "Bijapur", "Bommanahalli",
                "Byatarayanapura", "Chikballapur", "Chikmagalur", "Chitradurga",
                "Dasarahalli", "Davanagere", "Gadag", "Gangawati", "Gokak",
                "Gulbarga", "Hassan", "Haveri", "Hospet", "Hubli", "Karwar",
                "Kolar", "Koppal", "Krishnarajapura", "Mahadevapura", "Mandya",
                "Mangalore", "Mysore", "Nipani", "Raichur", "Ramanagaram",
                "Ranibennur", "Sagar", "Shimoga", "Sirsi", "Tumkur", "Udupi",
                "Yadgir", "Yelahanka"};
        return Karnataka;
    }

    public String[] getKeralaCities() {
        String[] Kerala = { "Alappuzha", "Beypore", "Changanacheri",
                "Cherthala", "Cheruvannur", "Chittur", "Edathala", "Guruvayur",
                "Kalamassery", "Kanhangad", "Kannur", "Kasaragod", "Kayamkulam",
                "Kochi", "Kodungallur", "Kollam", "Kottayam", "Kozhikode",
                "Kunnamkulam", "Malappuram", "Manjeri", "Nedumangad",
                "Neyyattinkara", "Palakkad", "Payyannur", "Ponnani", "Quilandi",
                "Taliparamba", "Thalassery", "Thiruvananthapuram",
                "Thrippunithura", "Thrissur", "Tirur", "Tiruvalla", "Vadakara"};
        return Kerala;
    }

    public String[] getLakshadweepCities() {
        String[] Lakshadweep = { "Kavaratti"};
        return Lakshadweep;
    }

    public String[] getMadhyaPradeshCities() {
        String[] Madhya = { "Balaghat", "Betul", "Bhind",
                "Bina-Etawa", "Burhanpur", "Chhatarpur", "Chhindwara", "Damoh",
                "Dewas", "Dhar", "Guna", "Gwalior", "Harda", "Hoshangabad",
                "Itarsi", "Jabalpur", "Jabalpur Cantonment", "Khandwa", "Khargone",
                "Mandla", "Mandsaur", "Mhow", "Morena", "Murwara", "Narsimhapur",
                "Nimach", "Pithampur", "Ratlam", "Rewa", "Sagar", "Satna",
                "Sehore", "Seoni", "Shahdol", "Shajapur", "Shivpuri", "Singrauli",
                "Tikamgarh", "Ujjain", "Vidisha"};
        return Madhya;
    }

    public String[] getMaharashtraCities() {
        String[] Maharashtra = { "Ahmadnagar", "Akola", "Amarnath",
                "Amravati", "Aurangabad", "Badlapur", "Baramati", "Bhiwandi",
                "Bhusawal", "Chandrapur", "Dhule", "Dombivali", "Ichalkaranji",
                "Jalgaon", "Jalna", "Kalyan", "Karad", "Khopoli", "Kolhapur",
                "Latur", "Lonavale", "Mira Bhayandar", "Nalasopara", "Nanded",
                "Nashik", "Navi Mumbai", "Palghar", "Panvel", "Parbhani",
                "Pimpri-Chinchwad", "Pune Cantonment", "Ratnagiri",
                "Sangli (-Miraj)", "Satara", "Solapur", "Ulhasnagar", "Vasai",
                "Virar", "Wardha", "Yavatmal"};
        return Maharashtra;
    }

    public String[] getManipurCities() {
        String[] Manipur = { "Imphal"};
        return Manipur;
    }

    public String[] getMeghalayaCities() {
        String[] Meghalaya = { "Shillong", "Tura"};
        return Meghalaya;
    }

    public String[] getMizoramCities() {
        String[] Mizoram = { "Aizwal"};
        return Mizoram;
    }

    public String[] getNagalandCities() {
        String[] Nagaland = { "Dimapur", "Kohima"};
        return Nagaland;
    }

    public String[] getOrissaCities() {
        String[] Orissa = { "Balangir", "Baleshwar", "Barbil",
                "Bargarh", "Baripada", "Bhadrak", "Bhawanipatna", "Bhubaneswar",
                "Brahmapur", "Brajarajnagar", "Cuttack", "Dhenkanal", "Jatani",
                "Jaypur", "Jharsuguda", "Kendujhar", "Paradip", "Puri", "Raurkela",
                "Raurkela Township", "Rayagada", "Sambalpur", "Sunabeda"};
        return Orissa;
    }

    public String[] getPondicherryCities() {
        String[] Pondicherry = { "Karaikal", "Ozhukarai",
                "Pondicherry"};
        return Pondicherry;
    }

    public String[] getPunjabCities() {
        String[] Punjab = { "Abohar", "Amritsar", "Barnala",
                "Batala", "Bathinda", "Faridkot", "Fazilka", "Firozpur",
                "Firozpur Cantonment", "Gobindgarh", "Gurdaspur", "Hoshiarpur",
                "Jagraon", "Jalandhar", "Kapurthala", "Khanna", "Kot Kapura",
                "Maler Kotla", "Malout", "Mansa", "Moga", "Mohali", "Muktsar",
                "Nabha", "Pathankot", "Patiala", "Phagwara", "Rajpura",
                "S.A.S. Nagar", "Sangrur", "Sirhind", "Sunam", "Tarn Taran"};
        return Punjab;
    }

    public String[] getRajasthanCities() {
        String[] Rajasthan = { "Ajmer", "Alwar", "Balotra",
                "Banswara", "Baran", "Barmer", "Beawar", "Bharatpur", "Bhilwara",
                "Bikaner", "Bundi", "Chittaurgarh", "Chomun", "Churu", "Dausa",
                "Dhaulpur", "Fatehpur", "Ganganagar", "Hanumangarh", "Jaisalmer",
                "Jhunjhunun", "Jodhpur", "Karauli", "Kishangarh", "Kota", "Ladnun",
                "Makrana", "Nagaur", "Nimbahera", "Pali", "Rajgarh", "Rajsamand",
                "Ratangarh", "Sardarshahar", "Sawai Madhopur", "Sikar",
                "Sujangarh", "Suratgarh", "Tonk", "Udaipur"};
        return Rajasthan;
    }

    public String[] getSikkimCities() {
        String[] Sikkim = { "Gangtok"};
        return Sikkim;
    }

    public String[] getTamilCities() {
        String[] Tamil = { "Alandur", "Ambattur", "Arakonam",
                "Avadi", "Chengalpattu", "Chidambaram", "Coimbatore", "Coonoor",
                "Cuddalore", "Dharmapuri", "Dindigul", "Erode", "Hosur",
                "Kanchipuram", "Karur", "Krishnagiri", "Kumbakonam", "Madavaram",
                "Madurai", "Mayiladuthurai", "Nagercoil", "Namakkal", "Pollachi",
                "Pudukkottai", "Salem", "Sivakasi", "Tambaram", "Thanjavur",
                "Theni", "Thiruvarur", "Thoothukkudi", "Tindivanam",
                "Tiruchirappalli", "Tirunelveli", "Tiruvannamalai",
                "Udhagamandalam", "Vellore", "Viluppuram", "Virudhunagar"};
        return Tamil;
    }

    public String[] getTripuraCities() {
        String[] Tripura = { "Agartala"};
        return Tripura;
    }

    public String[] getUttaranchalCities() {
        String[] Uttaranchal = { "Dehra Dun", "Haldwani", "Hardwar",
                "Kashipur", "Rishikesh", "Roorkee", "Rudrapur"};
        return Uttaranchal;
    }


    public String[] getUttarPradeshCities() {
        String[] UttarPradesh = { "Agra Cantonment", "Aligarh",
                "Allahabad", "Amroha", "Azamgarh", "Bareilly", "Behta Hajipur",
                "Bijnor", "Bulandshahr", "Dadri", "Etawah", "Faizabad",
                "Farrukhabad", "Firozabad", "Ghaziabad", "Gonda", "Gorakhpur",
                "Hapur", "Jaunpur", "Jhansi", "Kanpur Cantonment",
                "Lucknow Cantonment", "Mathura", "Meerut", "Meerut Cantonment",
                "Mirzapur", "Modinagar", "Moradabad", "Muzaffarnagar", "Noida",
                "Raebareli", "Rampur", "Saharanpur", "Shahjahanpur", "Shamli",
                "Sitapur", "Sultanpur", "Unnao", "Varanasi", "Vrindavan"};
        return UttarPradesh;
    }

    public String[] getWestBengalCities() {
        String[] West = { "Alipurduar", "AsansolMC", "Baharampore",
                "Bally", "Bankura", "Baranagar", "Barasat", "Bardhaman",
                "Bulandshahr", "Barrackpore", "Baruipur", "Bolpur", "Bongaon",
                "Chandannagar M.C.", "Darjeeling", "Diamond Harbour", "Dum Dum",
                "DurgapurMC", "Habra", "Haldia", "Hooghly-Chinsurah", "Howrah",
                "Kalyani", "Kanchrapara", "Kharagpur", "Konnagar", "Krishnanagar",
                "Madhyamgram", "Medinipur", "Murshidabad", "Naihati",
                "North Dum Dum", "Old Malda", "Purulia", "Raiganj",
                "Rajarhat-Gopalpore", "Ranaghat", "Serampore", "SiliguriM.C.",
                "South Dum Dum", "Uttarpara Kotrung"};
        return West;
    }


    public void setStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void showMsg(String msg,RelativeLayout layout) {
        Snackbar snackbar = Snackbar
                .make(layout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public Typeface setfontRegular(Context context) {
        Typeface typefaceRegualr = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-Regular.ttf");
        return typefaceRegualr;
    }
    public Typeface setfontSemiBold(Context context) {
        Typeface typefaceRegualr = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-Semibold.ttf");
        return typefaceRegualr;
    }
    public Typeface setfontLight(Context context) {
        Typeface typefaceRegualr = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-Light.ttf");
        return typefaceRegualr;
    }
    public void sendScrollDown(final ScrollView scrollView){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        }).start();
    }
    public LatLng convertAddress(String address) {
        if (address != null && !address.isEmpty()) {
            try {
                List<Address> addressList = Aplication.coder.getFromLocationName(address, 1);
                if (addressList != null && addressList.size() > 0) {
                    double lat = addressList.get(0).getLatitude();
                    double lng = addressList.get(0).getLongitude();
                    p1 = new LatLng(lat, lng);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } // end catch
        } // end if
        return p1;
    }
    public LatLng getCurrentCountry(Context context){
        String country = context.getResources().getConfiguration().locale.getDisplayCountry();
        country="India";
        if (country != null && !country.isEmpty()) {
            try {
                List<Address> addressList = Aplication.coder.getFromLocationName(country, 1);
                if (addressList != null && addressList.size() > 0) {
                    double lat = addressList.get(0).getLatitude();
                    double lng = addressList.get(0).getLongitude();
                    p1 = new LatLng(lat, lng);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } // end catch
        } // end if
        return p1;
    }
}
