public class CharacterSearchTree
{ 
    private HuffmanTriple content;
    private CharacterSearchTree leftChild, rightChild;

    public CharacterSearchTree() 
    {
        content = null;
        leftChild = null;
        rightChild = null;
    }

 /* 1 - Konstruktor mit Feld von char
Implementieren Sie einen Konstruktor, der ein Feld von Zeichen (also des Typs char) als Parameter besitzt und der für die
im Feld abgelegten Zeichen Knoten in den Baum in der Reihenfolge ihres Auftretens im Feld einfügt oder – falls bereits ein
entsprechender Knoten vorhanden ist – die zugehörige Häufigkeit erhöht.*/
    public CharacterSearchTree(char[] c){
      
      for(int i = 0; i < c.length; i++){

      add(c[i]);
      }
       
      
          
    }

    /*2 - Methode void add( char t, int q, String c ) mit drei Parametern
Implementieren Sie eine Methode add( char t, int q, String c ), die ein Zeichen t zusammen mit der Häufigkeit q und der Kodierung c in den Baum einträgt. Ist für das Zeichen t im Baum noch kein Knoten vorhanden, soll dieser
ergänzt werden. Ist für das Zeichen t im Baum bereits ein Knoten vorhanden, soll dessen Häufigkeit um den Wert von q
erhöht und die Kodierung auf c gesetzt werden. */

    public void add(char t, int q, String c){
     
      if(isEmpty()){
      
            content = new HuffmanTriple( t, q );
            content.setCode(c);
            leftChild = new CharacterSearchTree();
            rightChild = new CharacterSearchTree();
      } else{ 
        
        if ( content.getToken() > t )
            {
             
                leftChild.add( t, q, c );
            }
            else if ( content.getToken() < t )
            {
              
                rightChild.add( t,q,c );
            }
            else
            {
                 
                for(int i = 0; i <= q; i++){
                content.incrementQuantity();
                }

                content.setCode(c);
            }
      
      } 

    }

    /* 3 - Methode void showPreOrder()
Implementieren Sie eine Methode showPreOrder, die die Inhalte der Knoten des Baums in der Folge eines PreOrderDurchlaufs anzeigt. Bei einem PreOrder-Durchlauf wird zuerst der Inhalt der Wurzel ausgegeben und danach werden der
linke und danach der rechte Teilbaum in dieser Reihenfolge behandelt. Nutzen Sie die Methode toString der Klasse
HuffmanTriple. Wird der Inhalt eines Blatts ausgegeben, soll ein ’*’ vorangestellt werden.*/

public void showPreOrder(){
 
if(!isEmpty()){
  if(isLeaf()){
    System.out.println("*");
  }
  
  System.out.println(content.toString());
  leftChild.showPreOrder();
  rightChild.showPreOrder();
  
} /*else{
  System.out.println(leftChild);
}*/

}

/*4 - Methode int height()
Implementieren Sie eine Methode height, die die Höhe des Baums liefert. Die Höhe des Baums ist die Anzahl der Knoten
auf dem längsten möglichen Weg von der Wurzel zu einem Blatt. Ein leerer Baum hat die Höhe 0. Ein Baum, der nur aus der
Wurzel besteht, hat die Höhe 1. */
public int height() {
if(!isEmpty()){
    int lefth = leftChild.height();
    int righth = rightChild.height();
    
    if (lefth > righth) {
        return lefth + 1;
    } else {
        return righth + 1;
    }
} else{
  return 0;
}
    
}

/* 5 - Methode int countCharacters()
Implementieren Sie eine Methode countCharacters, die die Summe der quantity-Werte aller HuffmanTripleObjekte im Baum bildet. Ein leerer Baum besitzt kein HuffmanTriple-Objekt, liefert also als Ergebnis 0.*/

public int countCharacters(){
  if(!isEmpty()){
  int q = content.getQuantity();
  return q + leftChild.countCharacters() + rightChild.countCharacters();

  } else{
    return 0;
  }


}

/* 6 - Methode int longestCode()
Implementieren Sie eine Methode longestCode, die die Länge der längsten Kodierung aus allen HuffmanTripleObjekten des Baums bestimmt. Ein leerer Baum besitzt kein HuffmanTriple-Objekt und damit auch keine Kodierung,
liefert also als Ergebnis 0. */

public int longestCode(){
   if ( !isEmpty() ) 
        {
            int leftLength = leftChild.longestCode();
            int rightLength = rightChild.longestCode();
            int longestChildLength = 0;
            if ( leftLength > rightLength )
            {
                longestChildLength = leftLength;
            } else {
                longestChildLength = rightLength;
            }
            if ( longestChildLength > content.getCode().length() )
            {
                return longestChildLength;
            } else {
                return content.getCode().length();
            }
        } else {
            return 0;
        }
}

/* 7 - Methode HuffmanTriple minimum()
Implementieren Sie eine Methode minimum, die das HuffmanTriple-Objekt mit dem kleinsten im Baum gespeicherten
token-Zeichen liefert. Implementieren Sie minimum mit einem nicht-rekursiven Algorithmus. Beachten Sie dabei, dass ein
binärer Suchbaum vorliegt. Wird die Methode für den leeren Teilbaum aufgerufen, soll null zurückgegeben werden. */

public HuffmanTriple minimum(){
  
  if(content == null){
    return null;
  }

  HuffmanTriple[] arr = toArray();
  HuffmanTriple min = arr[0];
  
  for(int i=1;i<arr.length;i++){
    
    if(arr[i].getToken() < min.getToken()){
	    min = arr[i];
	  }

  }

  return min;
}

/* 8 - Methode boolean hasOnlyCompleteNode()
Implementieren Sie eine Methode hasOnlyCompleteNodes, die prüft, ob in einem Baum keine Knoten mit nur einem
Nachfolger vorkommen. In diesem Fall soll true zurückgegeben werden, sonst false. Ein leerer Baum soll true liefern. */

public boolean hasOnlyCompleteNode(){
  if(content == null || isLeaf()){
    return true;
  } else{
  if(!leftChild.isEmpty() || rightChild.isEmpty()){
    return leftChild.hasOnlyCompleteNode() && rightChild.hasOnlyCompleteNode();
  } else{
    return false;
  }
  }


} 

/* 9 - Methode boolean containsCharacter( char t )
Implementieren Sie eine Methode containsCharacter, die prüft, ob ein als Parameter übergebenes Zeichen im Baum als
Wert des Attributs token eines HuffmanTriple-Objekts auftritt. In diesem Fall soll true zurückgegeben werden, sonst
false. Ein leerer Baum soll false liefern. */

public boolean containsCharacter(char t){
  
  if(!isEmpty()){
    if(content.getToken() > t){
      return leftChild.containsCharacter(t);
    } else if( content.getToken() < t){
       return rightChild.containsCharacter(t);
    } else{
    return true;
    }
  
  }
return false;
 
} 

/* 10 - Methode boolean equalStructure( CharacterSearchTree cst )
Implementieren Sie eine Methode equalStructure, die einen Parameter des Typs CharacterSearchTree besitzt. Die
Methode soll true zurückgeben, falls der aufrufende Baum und der als Argument übergebene Baum die gleiche Struktur
besitzen, also an den gleichen Positionen in den Bäumen Knoten bzw. Nachfolger auftreten. Die Inhalte der Knoten sollen
dabei unberücksichtigt bleiben. */

public boolean equalStructure(CharacterSearchTree cst){
  if(isEmpty()){
    return cst.isEmpty();
  } else if(cst.isEmpty()){
    return false;
  } else{
    return leftChild.equalStructure(cst.leftChild) && rightChild.equalStructure(cst.rightChild);
  }
}

/* 11 - Methode CharacterSearchTree rotateNodeToRight()
Implementieren Sie eine Methode rotateNodeToRight, die eine Rückgabe vom Typ CharacterSearchTree liefert.
Die Methode soll den Baum derart umformen, dass das linke Kind der Wurzel zur (neuen) Wurzel wird. Die Teilbäume der
betroffenen Knoten sollen unverändert bleiben. Die neue Wurzel soll zurückgegeben werden. Ist der Baum leer oder besitzt
die Wurzel kein linkes Kind, soll nichts geschehen und die (alte) Wurzel zurückgegeben werden.
Hinweis: Beachten Sie, dass das folgende Beispiel nur einen Sonderfall der Rotation behandelt.*/

public CharacterSearchTree rotateNodeToRight(){
  CharacterSearchTree newRoot = this;
  if(!isEmpty() && !leftChild.isEmpty()){
    newRoot = leftChild;
    leftChild = newRoot.rightChild;
    newRoot.rightChild = this;
  }
  return newRoot;
  

}

/* 12 - Methode boolean samePath( char t1, char t2 )
Implementieren Sie eine Methode boolean samePath( char t1, char t2 ), die true liefern soll, wenn es
einen direkten Pfad zwischen der Wurzel und einem (beliebigen) Blatt gibt, auf dem (in beliebiger Reihenfolge) ein
HuffmanTriple-Objekt dem dem Zeichen t1 als Attributwert von token und ein HuffmanTriple-Objekt dem
dem Zeichen t2 als Attributwert von token liegen. Sonst soll false zurückgegeben werden. */

public boolean samePath( char t1, char t2 )
    {
        boolean foundT1 = false;
        boolean foundT2 = false;
        CharacterSearchTree current = this;                                                

        while ( !current.isEmpty() )
        {
            if ( ! foundT1 )
            {
                if ( current.getContent().getToken() == t1 )
                {
                    foundT1 = true;
                }
                else 
                {
                    if ( current.getContent().getToken() == t2 )
                    {
                        foundT2 = true;
                    }
                    if ( current.getContent().getToken() > t1 )
                    {
                        current = current.leftChild;
                    }
                    else 
                    {
                        current = current.rightChild;
                    }
                }
            }
            else 
            {
                if ( foundT2 || current.getContent().getToken() == t2 )
                {
                    return true;
                }
                if ( current.getContent().getToken() > t2 )
                {
                    current = current.leftChild;
                }
                else 
                {
                    current = current.rightChild;
                }
            }
        }

        return false;
    }

/* Testat */

public int test1(){
  if(!isEmpty()){



    if(isLeaf() && content.getQuantity() > 1){
      return 1 + leftChild.test1() + rightChild.test1();
    }

    return leftChild.test1() + rightChild.test1();

  } else {
    return 0;
  }
}

public int test2(){
  if(!isEmpty()){
    if(!leftChild.isEmpty() && !rightChild.isEmpty()){
      
      return leftChild.test2() + rightChild.test2();
    } else{
      if(content.getQuantity() == 1){
      return 1 + leftChild.test2() + rightChild.test2();

      } else {
        return leftChild.test2() + rightChild.test2();
      }
    }

  } else{
    return 0;
  }
}

public int test3(){
  if(!isEmpty()){
    if(leftChild.isEmpty() && rightChild.isEmpty()){
      return leftChild.test3() + rightChild.test3();
    } else{
      if(!isLeaf() && content.getQuantity() < 3){
        return 1 + leftChild.test3() + rightChild.test3();
      } else {
        return leftChild.test3() + rightChild.test3();
      }
    }
  } else{
    return 0;
  }
}


    public HuffmanTriple getContent()
    {
        if ( !isEmpty() )
        {
            return content;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isEmpty() 
    {
        return content == null;
    }

    public boolean isLeaf() 
    {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    public void add( char t )
    {
        if ( isEmpty() ) 
        {
            content = new HuffmanTriple( t );
            leftChild = new CharacterSearchTree();
            rightChild = new CharacterSearchTree();
        }
        else
        {
            if ( content.getToken() > t )
            {
                leftChild.add( t );
            }
            else if ( content.getToken() < t )
            {
                rightChild.add( t );
            }
            else
            {
                content.incrementQuantity();
            }
        }
    }

    public void iterativeAdd( char t )
    {
        CharacterSearchTree current = this;
        while ( !current.isEmpty() && current.content.getToken() != t )
        {
            if ( current.content.getToken() > t )
            {
                current = current.leftChild;
            }
            else
            {
                current = current.rightChild;
            }
        }
        if ( current.isEmpty() ) 
        {
            current.content = new HuffmanTriple( t );
            current.leftChild = new CharacterSearchTree();
            current.rightChild = new CharacterSearchTree();
        }
        else
        {
            current.content.incrementQuantity();
        }
    }

    public String getCode( char t )
    {
        if ( !isEmpty() ) 
        {
            if ( content.getToken() > t )
            {
                return leftChild.getCode( t );
            }
            else if ( content.getToken() < t )
            {
                return rightChild.getCode( t );
            }
            else
            {
                return content.getCode();
            }
        }
        else
        {
            throw new IllegalStateException();
        }
    }
    
    public int size() 
    {
        if ( isEmpty() ) 
        {
            return 0;
        }
        else
        {
            return 1 + leftChild.size() + rightChild.size();
        }       
    }

    public void show()
    {
        if ( !isEmpty() ) 
        {
            leftChild.show();
            System.out.println( content.toString() );
            rightChild.show();
        }
    }

    public HuffmanTriple[] toArray()
    {
        if ( !isEmpty() ) 
        {
            HuffmanTriple[] collector = new HuffmanTriple[size()];
            toArray( collector, 0 );
            return collector;
        }
        return new HuffmanTriple[0];
    }

    private int toArray( HuffmanTriple[] collector, int index ) 
    {
        if ( !isEmpty() )
        {
            index = leftChild.toArray( collector, index );
            collector[index] = content;
            index = rightChild.toArray( collector, index + 1 );
        }
        return index;
    }  


   

  
}
