package derevo;

import java.util.Stack;
/*Для вывода всего бинарного дерева воспользуемся внутренним классом java stack.*/
class Tree {
    /*Класс дерева.*/
   private Yzel cornYz; 
   /*Ввод корневого узла.*/
   public Tree() { 
       /*Создание пустого дерева.*/
        cornYz = null;
   }

   public Yzel oneYzelZn(int one) { 
       /*Программа находит узел по значению*/
       Yzel oldYzel = cornYz; 
       /*Поиск начинается с корневого узла.*/
       while (oldYzel.getOne() != one) {
           /*Поиск продолжается пока не найдется узел или не будет конец дерева.*/
           if (one < oldYzel.getOne()) { 
               /*Поиск по  ветви дерева влево.*/
               oldYzel = oldYzel.getLz();
           } else { 
               /*Поиск по  ветви дерева вправо.*/
               oldYzel = oldYzel.getRz();
           }
           if (oldYzel == null) { 
               return null;
               /*Если у узла нет потомков, то возвращается нуль, как конец дерева.*/
           }
       }
       return oldYzel; 
       /*Если найдено значение, то программа его возвращает.*/
   }

   public void insertYzel(int one) { 
       /*Метод вставки нового элемента*/
       Yzel newYzel = new Yzel(); 
       /*Создание нового узла*/
       newYzel.setOne(one);
       /*Вставка данных в узел*/
       if (cornYz == null) { 
           cornYz = newYzel;
           /*Если нет потомков, то данный узел и есть главный узел.*/
       }
       else { 
           /*Корневой узел есть.*/
           Yzel oldYzel = cornYz; 
           /*Программа начинает идти с корневого узла.*/
           Yzel fatherYzel;
           while (true) 
               /*Программа проходит по дереву, пока не будет конца дерева.*/
           {
               fatherYzel = oldYzel;
               /*Инициализация текущеко элемента дерева.*/
               if(one == oldYzel.getOne()) {   
                   /*Если такой элемент в дереве уже есть, не сохраняем его*/
                    return;    
                 /*Выход из метода.*/
                }
                else  if (one < oldYzel.getOne()) {   
                    /*Произходит движение влево.*/
                   oldYzel = oldYzel.getLz();
                   if (oldYzel == null){ 
                       /*Конец пути по ветви.*/
                       fatherYzel.setLz(newYzel); 
                       /*Программа идет влево по ветви и выходит из мктода.*/
                       return;
                   }
               }
               else { 
                    /*Если программа идет по правому предку.*/
                   oldYzel = oldYzel.getRz();
                   if (oldYzel == null) { 
                       fatherYzel.setRz(newYzel); 
                       return;
                       /*Выход из метода.*/
                   }
               }
           }
       }
   }

   public boolean deleteYzel(int one) 
           /*Удаление узла с заданием ключа.*/
   {
       Yzel oldYzel = cornYz;
       Yzel fatherYzel = cornYz;
       boolean isLeftChild = true;
       while (oldYzel.getOne() != one) { 
           /*Находим узел который нужно удалить.*/
           fatherYzel = oldYzel;
           if (one < oldYzel.getOne()) { 
               /*Двигаемся влево.*/
               isLeftChild = true;
               oldYzel = oldYzel.getLz();
           }
           else { /*Движемся вправо.*/
               isLeftChild = false;
               oldYzel = oldYzel.getRz();
           }
           if (oldYzel == null)
               return false; 
           /*Такого узла не существует.*/
       }

       if (oldYzel.getLz() == null && oldYzel.getRz() == null) { 
           /*Если у узла нет потомков, то программа его просто удаляет.*/
           if (oldYzel == cornYz) 
               /*Если есть потомки, то удаляется корень и его потомки.*/
               cornYz = null;
           else if (isLeftChild)
               fatherYzel.setLz(null); 
           /*Если нет то узел отсоединяется от родителя.*/
           else
               fatherYzel.setRz(null);
       }
       /*Узел заменяется левым поддеревом если правого потомка нет.*/
       else if (oldYzel.getRz() == null) { 
           
           if (oldYzel == cornYz)
               cornYz = oldYzel.getLz();
           else if (isLeftChild)
               fatherYzel.setLz(oldYzel.getLz());
           else
               fatherYzel.setRz(oldYzel.getLz());
       }
       else if (oldYzel.getLz() == null) { 
           /*Узел заменяется правым поддеревом если левого потомка нет.*/
           if (oldYzel == cornYz)
               cornYz = oldYzel.getRz();
           else if (isLeftChild)
               fatherYzel.setLz(oldYzel.getRz());
           else
               fatherYzel.setRz(oldYzel.getRz());
       }
       else { 
           /*Если есть два потомка, то узел заменяется преемником.*/
           Yzel heir = receiveHeir(oldYzel);
           /*Поиск преемника для удаления узла.*/
           if (oldYzel == cornYz)
               cornYz = heir;
           else if (isLeftChild)
               fatherYzel.setLz(heir);
           else
               fatherYzel.setRz(heir);
       }
       return true;
       /*Элемент удалён.*/
   }

   /*Метод возвращает узел со следующим значением после передаваемого аргументом.*/
   private Yzel receiveHeir(Yzel node) {
       Yzel parentNode = node;
       Yzel heirNode = node;
       Yzel oldYzel = node.getRz(); 
       /*Переход к правому потомку.*/
       while (oldYzel != null) /*Проходим по левым потомкам.*/
       {
           parentNode = heirNode;
           /*Потомок становится текущим узлом.*/
           heirNode = oldYzel;
           oldYzel = oldYzel.getLz(); 
           /*Переход к левому потомку.*/
       }
       if (heirNode != node.getRz()) 
           /*Определение является ли он потомком.*/
           /*Для правого потомка.*/
       { 
           /*Создание связи между узлами.*/
           parentNode.setLz(heirNode.getRz());
           heirNode.setRz(node.getRz());
       }
       return heirNode;
       /*Возврат потомка.*/
   }

   public void printTree() { 
       /*Метод для вывода дерева на экран.*/
       Stack bigStack = new Stack(); 
       /*Выделение памяти под общий стек.*/
       bigStack.push(cornYz);
       int rast = 32; 
       /*Изначальное растояние между элементами.*/
       boolean isRowEmpty = false;
       String cherta = "-----------------------------------------------------------------";
       System.out.println(cherta);
       /*Вывод черты на экран.*/
       while (isRowEmpty == false) {
           Stack localStack = new Stack(); 
           /*Выделение памяти под колальный стек.*/
           isRowEmpty = true;

           for (int j = 0; j < rast; j++)
               System.out.print(' ');
           while (bigStack.isEmpty() == false) { 
               /*Пока в общем стеке есть элементы.*/
               Yzel temp = (Yzel) bigStack.pop(); 
               /*Берем следующий элемент при этом удаляем его из стека.*/
               if (temp != null) {
                   /*Если в стеке не осталось элементов.*/
                   System.out.print(temp.getOne()); 
                   /*Вывод значения элемента в консоль.*/
                   localStack.push(temp.getLz()); 
                   /*Сохранение в кокальный стек текущего элемента.*/
                   localStack.push(temp.getRz());
                   if (temp.getLz() != null ||
                           temp.getRz() != null)
                       isRowEmpty = false;
               }
               else {
                   System.out.print("__");
                   /*Пустой элемент.*/
                   localStack.push(null);
                   localStack.push(null);
                   /*Ввод локального элемента в глобальный.*/
               }
               for (int j = 0; j < rast * 2 - 2; j++)
                   /*Вывод элементов через определенное расстояние.*/
                   System.out.print(' ');
           }
           System.out.println();
           rast = rast/2;
           /*Уменьшение растояния между элементами.*/
           while (localStack.isEmpty() == false)
               /*Пока не конец дерева.*/
               bigStack.push(localStack.pop()); 
           /*Перемещение маленького стека в большой стек.*/
       }
       System.out.println(cherta);
       /*Проводим черту.*/
   }
}
public class Derevo {
    /*Основное тело программы.*/

    public static void main(String[] args) {
        Tree tree = new Tree();
       /*Выделяем память под дерево.*/
       tree.insertYzel(6);
       tree.insertYzel(8);
       tree.insertYzel(5);
       tree.insertYzel(8);
       tree.insertYzel(2);
       tree.insertYzel(9);
       tree.insertYzel(7);
       tree.insertYzel(4);
       tree.insertYzel(10);
       tree.insertYzel(3);
       tree.insertYzel(1);
       tree.insertYzel(90);
       tree.insertYzel(10);
       tree.insertYzel(20);
       /*Ввод значений узлов бинарного дерева.*/
       tree.printTree();
       /*Вывод на экран бинарного дерева.*/
       tree.deleteYzel(10);
       /*Удаление узла.*/
       tree.printTree();
       /*Вывод на экран бинарного дерева после удаления узла.*/

       Yzel poiskYzel = tree.oneYzelZn(3);
       /*Находим значение элементу по индексу.*/
       poiskYzel.printYzel();
       /*Выввод значения элемента.*/
    }
    
}
