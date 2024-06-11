package com.example.mrgupazz.api.wordapi;

import java.util.ArrayList;

public class WordData {
    private static String [] wordA = {
            "absent", "absolute", "absolutely", "absorption", "abstract", "abstracts", "abu",
            "abuse", "ac", "academic", "academics", "academy", "acc", "accent", "accept",
            "acceptable", "acceptance", "accepted"
    };

    private static String[] wordB = {
            "baby", "back", "bacon", "bad", "balance", "ball", "balloon",
            "banana", "band", "bank", "bar", "bare", "bark", "barn",
            "base", "basic", "basket", "bat", "bath", "battle"
    };

    private static String[] wordC = {
            "cable", "cake", "calculate", "calendar", "call", "calm",
            "camera", "camp", "can", "cancel", "candy", "canvas",
            "cap", "capital", "car", "carbon", "card", "care", "career", "carpet"
    };

    private static String[] wordD = {
            "daily", "damage", "dance", "danger", "dark", "data",
            "date", "daughter", "day", "dead", "deal", "dear",
            "death", "debate", "debt", "decade", "december", "decide", "decision", "deep"
    };

    private static String[] wordE = {
            "eagle", "ear", "early", "earn", "earth", "ease",
            "east", "easy", "eat", "echo", "economic", "economy",
            "edge", "edit", "education", "effect", "effort", "egg", "eight", "either"
    };

    private static String[] wordF = {
            "face", "fact", "factor", "fail", "fair", "fall",
            "family", "famous", "fan", "far", "farm", "fast",
            "fat", "father", "fault", "favor", "fear", "feature", "february", "fee"
    };

    private static String[] wordG = {
            "gain", "game", "gap", "garage", "garden", "gas",
            "gate", "gather", "general", "generate", "generation", "gentle",
            "genuine", "geography", "gesture", "get", "giant", "gift", "girl", "give"
    };

    private static String[] wordH = {
            "habit", "hair", "half", "hall", "hand", "handle",
            "hang", "happen", "happy", "hard", "harm", "hat",
            "hate", "have", "head", "health", "hear", "heart", "heat", "heavy"
    };

    private static String[] wordI = {
            "ice", "idea", "ideal", "identify", "identity", "if",
            "ignore", "ill", "illegal", "illustrate", "image", "imagine",
            "impact", "implement", "importance", "important", "impress", "improve", "include", "income"
    };

    private static String[] wordJ = {
            "jacket", "jail", "jam", "january", "jar", "jazz",
            "jealous", "jeans", "jelly", "jewel", "job", "join",
            "joke", "journal", "journey", "joy", "judge", "juice", "jump", "junior"
    };

    private static String[] wordK = {
            "kangaroo", "keen", "keep", "kettle", "key", "kick",
            "kid", "kill", "kind", "king", "kiss", "kitchen",
            "knee", "knife", "knit", "knock", "know", "knowledge", "known", "keynote"
    };

    private static String[] wordL = {
            "label", "labor", "lack", "lady", "lake", "lamp",
            "land", "language", "large", "last", "late", "laugh",
            "law", "lawn", "lay", "lead", "leader", "leaf", "learn", "leave"
    };

    private static String[] wordM = {
            "machine", "magazine", "mail", "main", "maintain", "major",
            "make", "male", "man", "manage", "management", "manager",
            "manner", "many", "map", "mark", "market", "marriage", "marry", "mass"
    };

    private static String[] wordN = {
            "name", "narrow", "nation", "national", "native", "natural",
            "nature", "near", "nearly", "necessary", "neck", "need",
            "negative", "neighbor", "neither", "network", "never", "new", "news", "next"
    };

    private static String[] wordO = {
            "object", "observe", "obtain", "obvious", "occasion", "occur",
            "ocean", "october", "odd", "of", "off", "offer",
            "office", "often", "oil", "okay", "old", "olympic", "on", "once"
    };

    private static String[] wordP = {
            "pace", "pack", "page", "pain", "paint", "pair",
            "pale", "panel", "paper", "parent", "park", "part",
            "participate", "particular", "partner", "party", "pass", "past", "path", "patient"
    };

    private static String[] wordQ = {
            "qualify", "quality", "quarter", "queen", "question", "quick",
            "quiet", "quite", "quote", "quantum", "quantity", "quarrel",
            "query", "quest", "queue", "quotation", "quoted", "quaint", "quirky", "quench"
    };

    private static String[] wordR = {
            "race", "radio", "rain", "raise", "range", "rapid",
            "rare", "rate", "rather", "reach", "react", "read",
            "ready", "real", "realize", "really", "reason", "recall", "receive", "recent"
    };

    private static String[] wordS = {
            "sad", "safe", "sail", "salad", "salary", "sale",
            "salt", "same", "sample", "sand", "sat", "save",
            "say", "scale", "scan", "scar", "scare", "scene", "school", "science"
    };

    private static String[] wordT = {
            "table", "tackle", "tail", "take", "tale", "talk",
            "tall", "tank", "tap", "target", "task", "taste",
            "tax", "tea", "teach", "team", "tear", "technology", "television", "tell"
    };

    private static String[] wordU = {
            "umbrella", "unable", "uncle", "under", "undergo", "understand",
            "undertake", "uniform", "union", "unique", "unit", "unite",
            "universe", "university", "unknown", "unless", "unlike", "until", "unusual", "up"
    };

    private static String[] wordV = {
            "vacation", "vague", "valid", "valley", "value", "van",
            "variation", "variety", "various", "vast", "vegetable", "vehicle",
            "venture", "version", "very", "vessel", "veteran", "victim", "victory", "video"
    };

    private static String[] wordW = {
            "wage", "wait", "wake", "walk", "wall", "want",
            "war", "warm", "warn", "wash", "waste", "watch",
            "water", "wave", "way", "we", "weak", "wealth", "weapon", "wear"
    };

    private static String[] wordX = {
            "xenon", "xenophobia", "xerox", "x-ray", "xylophone", "x-axis",
            "xenial", "xenolith", "xerophyte", "xenogenesis", "xeroderma", "xiphoid",
            "xenograft", "xenobiotic", "xenobot", "xenopus", "xenon-filled", "xenomorph", "xenotime", "xenotropic"
    };

    private static String[] wordY = {
            "yacht", "yard", "yarn", "year", "yell", "yellow",
            "yes", "yesterday", "yet", "yield", "you", "young",
            "your", "yours", "yourself", "youth", "yummy", "yearly", "yawn", "yearn"
    };

    private static String[] wordZ = {
            "zebra", "zero", "zone", "zoo", "zoom", "zealous",
            "zenith", "zephyr", "zigzag", "zinc", "zip", "zodiac",
            "zombie", "zonal", "zoning", "zeal", "zest", "zillion", "zonal", "zeolite"
    };


    public static ArrayList<Word> getListData(String[] words) {
        ArrayList<Word> list = new ArrayList<>();
        for (String wordString : words) {
            Word word = new Word();
            word.setWord(wordString);
            list.add(word);
        }
        return list;
    }

    public static ArrayList<Word> getListDataA() {
        return getListData(wordA);
    }

    public static ArrayList<Word> getListDataB() {
        return getListData(wordB);
    }

    public static ArrayList<Word> getListDataC() {
        return getListData(wordC);
    }

    public static ArrayList<Word> getListDataD() {
        return getListData(wordD);
    }

    public static ArrayList<Word> getListDataE() {
        return getListData(wordE);
    }

    public static ArrayList<Word> getListDataF() {
        return getListData(wordF);
    }

    public static ArrayList<Word> getListDataG() {
        return getListData(wordG);
    }

    public static ArrayList<Word> getListDataH() {
        return getListData(wordH);
    }

    public static ArrayList<Word> getListDataI() {
        return getListData(wordI);
    }

    public static ArrayList<Word> getListDataJ() {
        return getListData(wordJ);
    }

    public static ArrayList<Word> getListDataK() {
        return getListData(wordK);
    }

    public static ArrayList<Word> getListDataL() {
        return getListData(wordL);
    }

    public static ArrayList<Word> getListDataM() {
        return getListData(wordM);
    }

    public static ArrayList<Word> getListDataN() {
        return getListData(wordN);
    }

    public static ArrayList<Word> getListDataO() {
        return getListData(wordO);
    }

    public static ArrayList<Word> getListDataP() {
        return getListData(wordP);
    }

    public static ArrayList<Word> getListDataQ() {
        return getListData(wordQ);
    }

    public static ArrayList<Word> getListDataR() {
        return getListData(wordR);
    }

    public static ArrayList<Word> getListDataS() {
        return getListData(wordS);
    }

    public static ArrayList<Word> getListDataT() {
        return getListData(wordT);
    }

    public static ArrayList<Word> getListDataU() {
        return getListData(wordU);
    }

    public static ArrayList<Word> getListDataV() {
        return getListData(wordV);
    }

    public static ArrayList<Word> getListDataW() {
        return getListData(wordW);
    }

    public static ArrayList<Word> getListDataX() {
        return getListData(wordX);
    }

    public static ArrayList<Word> getListDataY() {
        return getListData(wordY);
    }

    public static ArrayList<Word> getListDataZ() {
        return getListData(wordZ);
    }
}
