import java.util.*;

public class FileNavigator {
    private HashMap<String, ArrayList<FileData>> fileDataHashMap = new HashMap<>();

    public void add(String path, FileData file) throws InvalidPathException {
        if (!path.equals(file.getPath())) {
            throw new InvalidPathException();
        }
        if (fileDataHashMap.containsKey(path)) {
            fileDataHashMap.get(file.getPath()).add(file);
        } else {
            fileDataHashMap.put(path, new ArrayList<>(List.of(file)));
        }
    }

    public Optional<Byte> sumSize(FileData file1, FileData file2){
        byte sum=0;
        if((!(file1.getSize() <=0))&&(!(file2.getSize()<=0))) {
            sum = (byte) (file1.getSize() + file2.getSize());
        }
        return Optional.of(sum);
    }

    public Optional<Integer> getFileNumber(FileData file){
        String str="";
       for(int i=0;i<file.getName().length();i++){
           if(Character.isDigit(file.getName().charAt(i))){
               str+=file.getName().charAt(i);
           }
       }
       return Optional.of(Integer.valueOf(str));
    }


    public Optional<String> find(String path) {
       String str =null;
        if (fileDataHashMap.containsKey(path)) {
            str=fileDataHashMap.get(path).toString();
        }
        return Optional.ofNullable(str);
    }

    public List<FileData> filterBySize(byte aByte) {
        ArrayList<FileData> list = new ArrayList<>();
        for (List<FileData> fileDataList : fileDataHashMap.values()) {
            for (FileData fileData : fileDataList) {
                if (fileData.getSize() <= aByte) {
                    list.add(fileData);
                }
            }
        }
        return list;
    }

    public void remove(String path) {

        fileDataHashMap.remove(path);
    }

    public List<FileData> sortBySize() {
        ArrayList<FileData> arrayList = new ArrayList<>();
        for (ArrayList<FileData> fileData : fileDataHashMap.values()) {
            arrayList.addAll(fileData);
        }
        arrayList.sort(Comparator.comparing(FileData::getSize));
        return arrayList;
    }

    @Override
    public String toString() {
        return "FileNavigator{" +
                "fileDataHashMap=" + "\n" + fileDataHashMap +
                '}';
    }

    public FileNavigator(HashMap<String, ArrayList<FileData>> fileDataHashMap) {
        this.fileDataHashMap = fileDataHashMap;
    }

    public FileNavigator() {
    }
}
