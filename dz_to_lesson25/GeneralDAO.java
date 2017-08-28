package dz_to_lesson25;

public class GeneralDAO <T>{
    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[10];

    public T save(T t)throws Exception{
        if (t == null || array == null)
            throw new Exception("Can not saving object: " + t.toString());

        if (checkIsFull(array))
            throw new Exception("No free space to save");

        if (checkValidateObject(t))
            throw new Exception("This object already exists");

        int index = 0;
        for(T el : array){
            if (el == null){
                array[index] = t;
                return array[index];
            }
            index++;
        }
        return null;
    }

    public T[] getAll()throws Exception{
        if (array == null)
            throw new Exception("This array  is empty");

        return array;
    }

    private boolean checkIsFull(T[] array){
        if (array == null){
            return false;
        }

        int index = 0;
        for(T el : array){
            if (el != null){
                return true;
            }
            index++;
        }
        return false;
    }

    private boolean checkValidateObject(T t){
        if (t == null){
            return false;
        }

        int index = 0;
        for(T el : array){
            if (el != null && !el.equals(t)){
                return true;
            }
            index++;
        }
        return false;
    }
}
