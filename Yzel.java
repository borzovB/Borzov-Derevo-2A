package derevo;

public class Yzel {
    private int one;
    /*Ключ узла*/
   private Yzel lz; 
   /* Левый узел потомок*/
   private Yzel rz; 
   /*Правый узел потомок*/

   public void printYzel() { 
       /*Метод для вывода узла на экран.*/
       System.out.println("Y yzla esti znachenie: " + one);
   }

   public int getOne() {
       /*Метод инициализации узла.*/
       return this.one;
   }

   public void setOne(final int one) {
       /*Метод присворения значения узла.*/
       this.one = one;
   }

   public Yzel getLz() {
       /*Метод инициализации левого предка.*/
       return this.lz;
   }

   public void setLz(final Yzel lz) {
       this.lz = lz;
       /*Присвоение значения левому предку.*/
   }

   public Yzel getRz() {
       return this.rz;
       /*Метод инициализации правого предка.*/
   }

   public void setRz(final Yzel rz) {
       this.rz = rz;
       /*Метод присврения значения правому потомку узла.*/
   }

   @Override
   /*Структура вывода одного узла на экран.*/
   public String toString() {
       return "Yzel{" +
               "One=" + one +
               ", lz=" + lz +
               ", rz=" + rz +
               '}';
   }
}
