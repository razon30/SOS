package saddam.razon.sos.data;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import saddam.razon.sos.R;
import saddam.razon.sos.model.Country;

public class Globals {
    public static final String CON_AFRICA = "Africa";
    public static final String CON_ALL = "All Countries";
    public static final String CON_ASIA = "Asia";
    public static final String CON_AUST = "Australia and Oceania";
    public static final String CON_CA = "Central America and the Caribbean";
    public static final String CON_EUROPE = "Europe";
    public static final String CON_NA = "North America";
    public static final String CON_SA = "South America";
    public static final String FIRE = "Fire";
    public static final String GENERALEMERGENCY = "Any Emergency";
    public static final String MEDICAL = "Ambulance (Medical Emergency)";
    public static final String POLICE = "Police";
    public static final String CONTINENT_NAME = "continent";
    public static final String COUNTRY_NAME = "coountry_name";
    public static ArrayList<Country> countries;
    public static DBWrapper dbw = null;
    public static String default_country = "";
    public static boolean default_opened = false;
    public static String default_region = "";
    public static ArrayList<String> divisions;

    static {
        initdivisions();
        init();
    }

    public static void setDefault(String r, String c) {
        default_region = r;
        default_country = c;
        if (dbw != null) {
            dbw.setDefaultCR(r, c);
        }
    }

    public static void resetDefault() {
        setDefault("", "");
    }

    public static void initDB(Context c) {
        if (dbw == null) {
            dbw = new DBWrapper(c);
        }
        default_region = dbw.getDefaultRegion();
        default_country = dbw.getDefaultCountry();
    }

    public static DBWrapper getDB() {
        return dbw;
    }

    public static DBWrapper getDB(Context context) {
        if (dbw==null)dbw = new DBWrapper(context);
        return dbw;
    }


    public static void initdivisions() {
        divisions = new ArrayList();
        divisions.add(CON_AFRICA);
        divisions.add(CON_ASIA);
        divisions.add(CON_EUROPE);
        divisions.add(CON_AUST);
        divisions.add(CON_NA);
        divisions.add(CON_CA);
        divisions.add(CON_SA);
        divisions.add(CON_ALL);
    }

    public static ArrayList<String> getDivisions() {
        return divisions;
    }

    public static String getRegionDesc(String r) {
        int x = 0;
        if (r.equalsIgnoreCase(CON_ALL)) {
            x = countries.size();
        } else {
            for (int i = 0; i < countries.size(); i++) {
                if (((Country) countries.get(i)).getContinent().equalsIgnoreCase(r)) {
                    x++;
                }
            }
        }
        return x + " Countries";
    }

    public static String getRegion(String c) {
        for (int i = 0; i < countries.size(); i++) {
            if (((Country) countries.get(i)).getName().equalsIgnoreCase(c)) {
                return ((Country) countries.get(i)).getContinent();
            }
        }
        return null;
    }

    public static Country getCountry(String n) {
        for (int i = 0; i < countries.size(); i++) {
            if (((Country) countries.get(i)).getName().equalsIgnoreCase(n)) {
                return (Country) countries.get(i);
            }
        }
        return null;
    }

    public static ArrayList<String> getCountryNames(String div) {
        ArrayList<String> sl = new ArrayList();
        int i = 0;
        while (i < countries.size()) {
            if (div.equalsIgnoreCase(CON_ALL) || div.equalsIgnoreCase(((Country) countries.get(i)).getContinent())) {
                sl.add(((Country) countries.get(i)).getName());
            }
            i++;
        }
        Collections.sort(sl);
        return sl;
    }

    public static ArrayList<Country> getCountryList(String div) {
        ArrayList<Country> sl = new ArrayList();
        int i = 0;
        while (i < countries.size()) {
            if (div.equalsIgnoreCase(CON_ALL) || div.equalsIgnoreCase(((Country) countries.get(i)).getContinent())) {
                sl.add(((Country) countries.get(i)));
            }
            i++;
        }
        Collections.sort(sl, (lhs, rhs) -> {

            String lhsString = lhs.getName().toLowerCase().trim();
            String rhsString = rhs.getName().toLowerCase().trim();

            return lhsString.compareTo(rhsString);
        });
        return sl;
    }

    public static int getCountryFlagIds(String ctr) {
        for (int i = 0; i < countries.size(); i++) {
            if (((Country) countries.get(i)).getName().equalsIgnoreCase(ctr)) {
                return ((Country) countries.get(i)).getFlagId();
            }
        }
        return 0;
    }

    public static Country newCountry(String con, String c, int flagId, String g, String p, String m, String f) {
        Country ct = new Country(con, c, flagId);
        if (g != null && g.length() > 0) {
            ct.addNumbers(GENERALEMERGENCY, g);
        }
        if (p != null && p.length() > 0) {
            ct.addNumbers(POLICE, p);
        }
        if (m != null && m.length() > 0) {
            ct.addNumbers(MEDICAL, m);
        }
        if (f != null && f.length() > 0) {
            ct.addNumbers(FIRE, f);
        }
        return ct;
    }

    public static Country newCountry(String con, String c, int flagId, String p, String m, String f) {
        return newCountry(con, c, flagId, null, p, m, f);
    }

    public static Country newCountry(String con, String c, int flagId, String g) {
        return newCountry(con, c, flagId, g, null, null, null);
    }

    public static void init() {
        countries = new ArrayList();
        Country c = newCountry(CON_AFRICA, "Algeria", R.drawable.dz, "17", "14", "14");
        c.addNumbers("National Gendarmerie", "1055");
        c.addNumbers("Counter Terrorist Unit", "1548");
        c.addNumbers("Support for Children", "3033");
        countries.add(c);
        countries.add(newCountry(CON_AFRICA, "Chad", R.drawable.td, "17", null, "18"));
        countries.add(newCountry(CON_AFRICA, "Djibouti", R.drawable.dj, "17", null, "18"));
        c = newCountry(CON_AFRICA, "Egypt", R.drawable.eg, "122", "123", "180");
        c.addNumbers("Tourist Police", "126");
        c.addNumbers("Traffic Police", "128");
        c.addNumbers("Electricity Emergency", "121");
        c.addNumbers("Natural Gas Supply Emergency", "129");
        countries.add(c);
        countries.add(newCountry(CON_AFRICA, "Ghana", R.drawable.gh, "999", "191", "193", "192"));
        countries.add(newCountry(CON_AFRICA, "Mali", R.drawable.ml, "17", "15", "18"));
        countries.add(newCountry(CON_AFRICA, "Mauritius", R.drawable.mu, "999", "114", "115"));
        c = newCountry(CON_AFRICA, "Morocco", R.drawable.ma, null, "15", "15");
        c.addNumbers("City Police", "19");
        c.addNumbers("Country Police", "177");
        countries.add(c);
        countries.add(newCountry(CON_AFRICA, "Nigeria", R.drawable.ng, "199"));
        c = newCountry(CON_AFRICA, "South Africa", R.drawable.za, "10111", "10177", "10111");
        c.addNumbers("From Mobile Networks", "112");
        countries.add(c);
        c = newCountry(CON_AFRICA, "Tunisia", R.drawable.tn, "197", "190", "198");
        c.addNumbers("National Guard", "193");
        countries.add(c);
        countries.add(newCountry(CON_AFRICA, "Rwanda", R.drawable.rw, "112", null, null));
        countries.add(newCountry(CON_AFRICA, "Uganda", R.drawable.ug, "999", null, null));
        c = newCountry(CON_AFRICA, "Sudan", R.drawable.sd, "999");
        c.addNumbers("Traffic Police", "777777");
        countries.add(c);
        countries.add(newCountry(CON_AFRICA, "Sierra Leone", R.drawable.sl, "019", "999", null));
        c = newCountry(CON_AFRICA, "Zambia", R.drawable.zm, "999", "991", "993");
        c.addNumbers("From Mobile Networks", "112");
        countries.add(c);
        c = newCountry(CON_AFRICA, "Zimbabwe", R.drawable.zw, "999", "995", "994", "993");
        c.addNumbers("From Mobile Networks", "112");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Afghanistan", R.drawable.af, "119", "102", "119"));
        c = newCountry(CON_ASIA, "Bangladesh", R.drawable.bd, "100", "103", "102");
        c.addNumbers("General(Toll free)", "999");
        c.addNumbers(" Rapid Action Battalion", "101");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Bahrain", R.drawable.bh, "999"));
        c = newCountry(CON_ASIA, "China", R.drawable.cn, "110", "120", "119");
        c.addNumbers("Traffic accident", "122");
        c.addNumbers("Private ambulance service in Beijing,", "999");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Myanmar", R.drawable.mm, "191"));
        c = newCountry(CON_ASIA, "Hong Kong", R.drawable.hk, "999");
        c.addNumbers("From Mobile Phones", "112");
        countries.add(c);
        c = newCountry(CON_ASIA, "India", R.drawable.in, "100", "102", "101");
        c.addNumbers(POLICE, "2611");
        c.addNumbers(MEDICAL, "1298");
        c.addNumbers(MEDICAL, "108");
        c.addNumbers(MEDICAL, "112");
        c.addNumbers(FIRE, "2611");
        c.addNumbers("Chennai Traffic Police", "103");
        c.addNumbers("Delhi Traffic Police", "1095");
        c.addNumbers("Kolkata Traffic Police", "1073");
        countries.add(c);
        c = newCountry(CON_ASIA, "Indonesia", R.drawable.id, "110", "118", "113");
        c.addNumbers(MEDICAL, "119");
        c.addNumbers("For Mobile and satellite phones", "112");
        c.addNumbers("Search and rescue team", "115");
        c.addNumbers("Natural disaster", "129");
        c.addNumbers("Electricity", "123");
        countries.add(c);
        c = newCountry(CON_ASIA, "Iran", R.drawable.ir, "110", "110", "115", "125");
        c.addNumbers("From Mobile Phones", "112");
        countries.add(c);
        c = newCountry(CON_ASIA, "Israel", R.drawable.il, "100", "101", "102");
        c.addNumbers("From Mobile Phones", "112");
        c.addNumbers("Israel Electric Corporation", "103");
        c.addNumbers("Municipal hazards that are not emergencies", "106");
        countries.add(c);
        c = newCountry(CON_ASIA, "Japan", R.drawable.jp, "110", "119", "119");
        c.addNumbers("Emergency at Sea", "118");
        countries.add(c);
        c = newCountry(CON_ASIA, "Jordan", R.drawable.jo, "911");
        c.addNumbers(GENERALEMERGENCY, "112");
        countries.add(c);
        c = newCountry(CON_ASIA, "Kazakhstan", R.drawable.kz, "112", "102", "103", "101");
        c.addNumbers("Gas leaks", "104");
        countries.add(c);
        c = newCountry(CON_ASIA, "North Korea", R.drawable.kp, "119");
        c.addNumbers("From Mobile Phones", "112");
        countries.add(c);
        c = newCountry(CON_ASIA, "South Korea", R.drawable.kr, "112", "119", "119");
        c.addNumbers("National security hotline", "111");
        c.addNumbers("Reporting spies", "113");
        c.addNumbers("Reporting a child, mentally handicapped, or elderly person wandering", "182");
        c.addNumbers("Phone service provider", "114");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Kuwait", R.drawable.kw, "112"));
        c = newCountry(CON_ASIA, "Lebanon", R.drawable.lb, "112", "140", "175");
        c.addNumbers(POLICE, "999");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Macau", R.drawable.mo, "999"));
        c = newCountry(CON_ASIA, "Maldives", R.drawable.mv, "102");
        c.addNumbers("Civil defence", "118");
        c.addNumbers("Police service", "119");
        countries.add(c);
        c = newCountry(CON_ASIA, "Malaysia", R.drawable.my, "999");
        c.addNumbers("For GSM Handsets", "112");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Mongolia", R.drawable.mn, "100", "102", "103", "101"));
        c = newCountry(CON_ASIA, "Nepal", R.drawable.np, "100", "102", "101");
        c.addNumbers(POLICE, "103");
        c.addNumbers("For GSM Handsets", "112");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Oman", R.drawable.om, "9999"));
        c = newCountry(CON_ASIA, "Pakistan", R.drawable.pk, "15", "115", "16");
        c.addNumbers("Traffic police", "1915");
        c.addNumbers(MEDICAL, "1122");
        c.addNumbers("For GSM Handsets", "112");
        countries.add(c);
        c = newCountry(CON_ASIA, "Philippines", R.drawable.ph, "117");
        c.addNumbers("For Mobile Phones", "911");
        c.addNumbers("For Mobile Phones", "112");
        c.addNumbers("Motor Assistance (Metro Manilla)", "993");
        c.addNumbers("Child Abuse (Bantay Bata)", "163");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Qatar", R.drawable.qa, "999"));
        c = newCountry(CON_ASIA, "Saudi Arabia", R.drawable.saudi_arabia, "999", "997", "998");
        c.addNumbers("Traffic police", "993");
        c.addNumbers("Rescue emergency", "911");
        c.addNumbers("Rescue emergency", "112");
        c.addNumbers("Rescue emergency", "08");
        countries.add(c);
        c = newCountry(CON_ASIA, "Singapore", R.drawable.sg, "999", "995", "995");
        c.addNumbers("From Foreign Hand Phones", "112");
        c.addNumbers("From Foreign Hand Phones", "911");
        countries.add(c);
        c = newCountry(CON_ASIA, "Sri Lanka", R.drawable.lk, "119", "110", "111");
        c.addNumbers(POLICE, "118");
        c.addNumbers("Accident Service", "+94112691111");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Syria", R.drawable.sy, "112", "110", "113"));
        c = newCountry(CON_ASIA, "Taiwan", R.drawable.taiwan, "110", "119", "119");
        c.addNumbers("From GSM Handset", "112");
        countries.add(c);
        c = newCountry(CON_ASIA, "Tajikistan", R.drawable.tj, "112", "102", "103", "101");
        c.addNumbers("Gas Leaks", "104");
        countries.add(c);
        c = newCountry(CON_ASIA, "Thailand", R.drawable.th, "191", "1669", "199");
        c.addNumbers("EMS Center (Bangkok)", "1646");
        c.addNumbers("Tourist Police", "1155");
        countries.add(c);
        c = newCountry(CON_ASIA, "Turkey", R.drawable.tr, "155", "112", "110");
        c.addNumbers("Gendarmerie", "156");
        c.addNumbers("Coast Guard", "158");
        countries.add(c);
        c = newCountry(CON_ASIA, "United Arab Emirates", R.drawable.ae, "999", "998", "997");
        c.addNumbers(POLICE, "112");
        c.addNumbers(MEDICAL, "999");
        countries.add(c);
        countries.add(newCountry(CON_ASIA, "Vietnam", R.drawable.vn, "113", "115", "114"));
        c = newCountry(CON_EUROPE, "Albania", R.drawable.al, "129", "127", "128");
        c.addNumbers("Road Police", "126");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Armenia", R.drawable.am, "102", "103", "101");
        c.addNumbers("Search & Rescue", "108");
        c.addNumbers("Gas Leaks", "104");
        c.addNumbers("Traffic Police", "177");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Austria", R.drawable.at, "112", "133", "144", "122");
        c.addNumbers("Gas Leaks", "128");
        c.addNumbers("Alpine Rescue", "140");
        c.addNumbers("On-duty Medical Unit", "141");
        c.addNumbers("Crisis Hotline", "142");
        c.addNumbers("Support for Children and Teens", "147");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Belarus", R.drawable.by, "102", "103", "101");
        c.addNumbers("Gas Leaks", "104");
        c.addNumbers("Redirected to 101 on velcom GSM-operator mobile phones", "112");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Belgium", R.drawable.be, "112", "101", "100", "100");
        c.addNumbers("Missing Children", "110");
        c.addNumbers("Mental Problems/Suicide", "106");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Bosnia and Herzegovina", R.drawable.ba, "122", "124", "123");
        c.addNumbers("Emergency Number From Mobile Phone", "112");
        c.addNumbers("Emergency Number From Mobile Phone", "911");
        c.addNumbers("Information on Local Emergency Numbers", "08");
        countries.add(c);
        countries.add(newCountry(CON_EUROPE, "Bulgaria", R.drawable.bg, "112", "166", "150", "160"));
        c = newCountry(CON_EUROPE, "Croatia", R.drawable.hr, "112", "192", "94", "93");
        c.addNumbers("Search & Rescue at Sea", "9155");
        c.addNumbers("Road Help", "987");
        countries.add(c);
        countries.add(newCountry(CON_EUROPE, "Turkish Republic of Northern Cyprus", R.drawable.cy, "112", "115", "112", "199"));
        c = newCountry(CON_EUROPE, "Cyprus", R.drawable.cyprus, "112");
        c.addNumbers(GENERALEMERGENCY, "199");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Czech Republic", R.drawable.cz, "112", "158", "155", "150");
        c.addNumbers("Municipal police", "156");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Denmark", R.drawable.dk, "112");
        c.addNumbers("Nearest Police (non-urgent)", "114");
        countries.add(c);
        countries.add(newCountry(CON_EUROPE, "Estonia", R.drawable.ee, "112", "110", null, null));
        countries.add(newCountry(CON_EUROPE, "Faroe Islands", R.drawable.fo, "112"));
        countries.add(newCountry(CON_EUROPE, "Finland", R.drawable.fi, "112"));
        c = newCountry(CON_EUROPE, "France", R.drawable.fr, "112", "17", null, "18");
        c.addNumbers("Hospital-based Ambulance (SAMU)", "15");
        c.addNumbers("Fire Service-based Ambulance", "18");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Georgia", R.drawable.ge, "112", "022", "033", "011");
        c.addNumbers("Gas Leaks", "04");
        c.addNumbers("Police (new number)", "122");
        c.addNumbers("Ambulance (Medical Emergency) (new number)", "113");
        c.addNumbers("Fire (new number)", "111");
        c.addNumbers("Gas Leaks (new number)", "114");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Germany", R.drawable.de, "112");
        c.addNumbers(POLICE, "110");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Gibraltar", R.drawable.gi, "112", null, "190", "190");
        c.addNumbers(GENERALEMERGENCY, "199");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Greece", R.drawable.gr, "112", "100", "166", "199");
        c.addNumbers("Forest Fire", "1591");
        c.addNumbers("Coast Guard Emergency", "108");
        c.addNumbers("Counter-Narcotics Immediate Intervention", "109");
        c.addNumbers("Tourist Police", "171");
        c.addNumbers("Emergency Social Aid", "197");
        countries.add(c);
        countries.add(newCountry(CON_EUROPE, "Hungary", R.drawable.hu, "112", "107", "104", "105"));
        c = newCountry(CON_EUROPE, "Iceland", R.drawable.is, "112");
        c.addNumbers("Police (Non-urgent) Reykjavík Capital Area", "4441000");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Ireland", R.drawable.ie, "112");
        c.addNumbers(GENERALEMERGENCY, "999");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Italy", R.drawable.it, "112", null, "118", "115");
        c.addNumbers("State Police", "113");
        c.addNumbers("Carabinieri (National Gendarmerie)", "112");
        c.addNumbers("Forest Service", "1515");
        c.addNumbers("Guardia di Finanza (Customs/Financial Police)", "117");
        c.addNumbers("Coast Guard", "1530");
        c.addNumbers("Redirected to 112", "911");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Kosovo", R.drawable.kosovo, "911", null, null, null);
        c.addNumbers("Kosovo Police (From Cellphones)", "192");
        c.addNumbers("Kosovo Police (From Cellphones)", "922");
        c.addNumbers("Kosovo Police (From Fixed Line)", "92");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Latvia", R.drawable.lv, "112", "02", "03", "01");
        c.addNumbers("Gas Leaks", "04");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Lithuania", R.drawable.lt, "112", "02", "03", "01");
        c.addNumbers(POLICE, "102");
        c.addNumbers(POLICE, "022");
        c.addNumbers(MEDICAL, "103");
        c.addNumbers(MEDICAL, "033");
        c.addNumbers(FIRE, "101");
        c.addNumbers(FIRE, "011");
        countries.add(c);
        countries.add(newCountry(CON_EUROPE, "Luxembourg", R.drawable.lu, "112", "113", null, null));
        countries.add(newCountry(CON_EUROPE, "Republic of Macedonia", R.drawable.mk, "112", "192", "194", "193"));
        countries.add(newCountry(CON_EUROPE, "Malta", R.drawable.mt, "112", "191", "196", "199"));
        countries.add(newCountry(CON_EUROPE, "Moldova", R.drawable.md, "902", "903", "901"));
        c = newCountry(CON_EUROPE, "Monaco", R.drawable.mc, "112", "17", null, "18");
        c.addNumbers("Ambulance, Severe", "15");
        c.addNumbers("Ambulance, Less Severe", "18");
        c.addNumbers("Homeless", "115");
        countries.add(c);
        countries.add(newCountry(CON_EUROPE, "Montenegro", R.drawable.me, "112", "122", "124", "123"));
        c = newCountry(CON_EUROPE, "Netherlands", R.drawable.nl, "112");
        c.addNumbers("Police (non-urgent)", "09008844");
        c.addNumbers("Redirected to 112 on GSM Mobile Phones", "911");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Norway", R.drawable.no, "112", "113", "110");
        c.addNumbers("Police (non-urgent)", "02800");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Poland", R.drawable.pl, "112", "997", "999", "998");
        c.addNumbers("Municipal Wardens", "986");
        c.addNumbers("Natural Gas/LPG Emergencies", "992");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Portugal", R.drawable.pt, "112");
        c.addNumbers("Forest Fire", "117");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Romania", R.drawable.ro, "112", "955", "961", "981");
        c.addNumbers("Gendarmerie", "956");
        c.addNumbers("Civil Protection", "982");
        c.addNumbers("Family Violence", "983");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Russia", R.drawable.ru, "112", "02", "03", null);
        c.addNumbers("Fire, Search and Rescue", "01");
        c.addNumbers("Gas leaks", "04");
        countries.add(c);
        countries.add(newCountry(CON_EUROPE, "San Marino", R.drawable.sm, "113", "118", "115"));
        countries.add(newCountry(CON_EUROPE, "Serbia", R.drawable.rs, "112", "92", "94", "93"));
        countries.add(newCountry(CON_EUROPE, "Slovakia", R.drawable.sk, "112", "158", "155", "150"));
        countries.add(newCountry(CON_EUROPE, "Slovenia", R.drawable.si, "112", "113", null, null));
        c = newCountry(CON_EUROPE, "Spain", R.drawable.es, "112", null, "061", "080");
        c.addNumbers("National", "091");
        c.addNumbers("Local Police", "092");
        c.addNumbers(FIRE, "085");
        c.addNumbers("Civil Guard", "062");
        c.addNumbers("Mossos d'Esquadra (Catalan police)", "088");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Sweden", R.drawable.se, "112");
        c.addNumbers("Non-urgent Police", "11414");
        c.addNumbers("GENERALEMERGENCY Redirected to 112", "90000");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Switzerland", R.drawable.ch, "112", "117", "144", "118");
        c.addNumbers("Poison", "145");
        c.addNumbers("Road emergency", "140");
        c.addNumbers("Psychological Support (Free & Anonymous)", "143");
        c.addNumbers("Psychological Support for Teens & Children (Free & Anonymous)", "147");
        c.addNumbers("Helicopter Air-Rescue (Rega)", "1414");
        c.addNumbers("Air rescue (Air Glaciers) (in Valais only)", "1415");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Turkey", R.drawable.tr, "155", "112", "110");
        c.addNumbers("Gendarmerie", "156");
        c.addNumbers("Coast Guard", "158");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Ukraine", R.drawable.ua, "112", "102", "103", "101");
        c.addNumbers("Gas Leaks", "104");
        countries.add(c);
        c = newCountry(CON_EUROPE, "United Kingdom", R.drawable.gb, "112");
        c.addNumbers(GENERALEMERGENCY, "999");
        c.addNumbers("Non-Emergency Number for Police (England & Wales)", "101");
        c.addNumbers("Non-Emergency Number for Health Issues (England & Wales)", "111");
        countries.add(c);
        c = newCountry(CON_EUROPE, "Vatican City", R.drawable.va, "113", "118", "115");
        c.addNumbers("Any Emergency (Redirected to 113 on GSM networks)", "112");
        countries.add(c);
        c = newCountry(CON_AUST, "Australia", R.drawable.au, "000");
        c.addNumbers("From mobile phones", "112");
        c.addNumbers("Crime Stoppers", "1800333000");
        c.addNumbers("Threats to National Security", "1800123400");
        c.addNumbers("Non Emergency – Police Assistance Line", "131444");
        countries.add(c);
        countries.add(newCountry(CON_AUST, "Fiji", R.drawable.fj, "911", "911", "9170"));
        c = newCountry(CON_AUST, "New Zealand", R.drawable.nz, "111");
        c.addNumbers("Traffic (from mobile phones only)", "*555");
        countries.add(c);
        countries.add(newCountry(CON_AUST, "Solomon Islands", R.drawable.sb, "999"));
        countries.add(newCountry(CON_AUST, "Vanuatu", R.drawable.vu, "112"));
        c = newCountry(CON_NA, "Canada", R.drawable.ca, "911");
        c.addNumbers("Non Emergency (in some areas)", "311");
        c.addNumbers("Redirected to 911 on GSM mobile phones", "112");
        c.addNumbers("Provincial Police within the province of Ontario", "*677");
        c.addNumbers("Sûreté du Québec in the province of Quebec", "*4141");
        countries.add(c);
        c = newCountry(CON_NA, "Greenland", R.drawable.gl, null);
        c.addNumbers("From Mobile Phone", "112");
        countries.add(c);
        c = newCountry(CON_NA, "Mexico", R.drawable.mx, "066", "065", "068");
        c.addNumbers("Emergency Dispatcher In Densely Populated Areas", "911");
        countries.add(c);
        countries.add(newCountry(CON_NA, "Saint Pierre and Miquelon", R.drawable.pm, "17", "15", "18"));
        c = newCountry(CON_NA, "United States of America", R.drawable.us, "911");
        c.addNumbers("Non Emergency", "311");
        c.addNumbers("From GSM Mobile Phones", "112");
        countries.add(c);
        c = newCountry(CON_CA, "Guatemala", R.drawable.gt, "110", "120", "123");
        c.addNumbers("Private Medical Insurance", "911");
        countries.add(c);
        countries.add(newCountry(CON_CA, "El Salvador", R.drawable.sv, "911"));
        c = newCountry(CON_CA, "Costa Rica", R.drawable.cr, "911");
        c.addNumbers(GENERALEMERGENCY, "112");
        countries.add(c);
        countries.add(newCountry(CON_CA, "Panama", R.drawable.pa, "911"));
        countries.add(newCountry(CON_CA, "Barbados", R.drawable.bb, "211", "511", "311"));
        countries.add(newCountry(CON_CA, "Cayman Islands", R.drawable.ky, "911"));
        c = newCountry(CON_CA, "Dominican Republic", R.drawable.doo, "911");
        c.addNumbers(GENERALEMERGENCY, "112");
        countries.add(c);
        countries.add(newCountry(CON_CA, "Jamaica", R.drawable.jm, "119", "110", "110"));
        countries.add(newCountry(CON_CA, "Trinidad and Tobago", R.drawable.tt, "999", "990", "990"));
        countries.add(newCountry(CON_CA, "Nicaragua", R.drawable.ni, "118"));
        countries.add(newCountry(CON_CA, "Honduras", R.drawable.hn, "199"));
        c = newCountry(CON_SA, "Argentina", R.drawable.ar, "101", "107", "100");
        c.addNumbers("Emergency dispatcher for Buenos Aires (city), Santa Fe (city), Rosario (city), Salta and Buenos Aires (provinces)", "911");
        countries.add(c);
        countries.add(newCountry(CON_SA, "Bolivia", R.drawable.bo, "110", "118", "119"));
        c = newCountry(CON_SA, "Brazil", R.drawable.br, "190", "192", "193");
        c.addNumbers("Federal Highway Police", "191");
        c.addNumbers("Federal Police", "194");
        c.addNumbers("Civil Police", "197");
        c.addNumbers("State Highway Police", "198");
        c.addNumbers("Civil Defense", "199");
        c.addNumbers("Human Rights", "100");
        c.addNumbers("Emergency Number for Mercosul Area", "128");
        c.addNumbers("Redirected to Police Number (190) From Mobile Phones", "112");
        c.addNumbers("Redirected to Police Number (190)", "911");
        countries.add(c);
        countries.add(newCountry(CON_SA, "Chile", R.drawable.cl, "133", "131", "132"));
        c = newCountry(CON_SA, "Colombia", R.drawable.co, "112", "156", "132", "119");
        c.addNumbers(GENERALEMERGENCY, "123");
        c.addNumbers("Traffic Accidents", "127");
        c.addNumbers("GAULA (anti-kidnapping)", "165");
        countries.add(c);
        c = newCountry(CON_SA, "Ecuador", R.drawable.ec, "911", "101", "911", "102");
        c.addNumbers("All Emergencies in Guayaquil (from Mobile Phones)", "*112");
        c.addNumbers("All Emergencies in Guayaquil (from Landline)", "112");
        c.addNumbers("Traffic accidents in Guayaquil", "103");
        c.addNumbers("Red Cross", "131");
        countries.add(c);
        countries.add(newCountry(CON_SA, "French Guyana", R.drawable.gf, "17", "15", "18"));
        countries.add(newCountry(CON_SA, "Guyana", R.drawable.gy, "911", "913", "912"));
        countries.add(newCountry(CON_SA, "Paraguay", R.drawable.py, "911"));
        c = newCountry(CON_SA, "Peru", R.drawable.pe, "105", "117", "116");
        c.addNumbers("Civil Defense (Disasters)", "115");
        c.addNumbers("Domestic Violence Helpline", "100");
        countries.add(c);
        countries.add(newCountry(CON_SA, "Suriname", R.drawable.sr, "115"));
        countries.add(newCountry(CON_SA, "Uruguay", R.drawable.uy, "911"));
        countries.add(newCountry(CON_SA, "Venezuela", R.drawable.ve, "171"));
    }
}
