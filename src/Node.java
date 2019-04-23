
import java.util.*;

public class Node {

   private String name;
   private Node firstChild;
   private Node nextSibling;

   Node (String n, Node d, Node r) {

      name = n;
      firstChild = d;
      nextSibling = r;
   }

   public static Node parsePostfix (String s) {

      //lisasin veel expetioneid.

      //https://www.programcreek.com/java-api-examples/org.w3c.dom.Node <-- abiks node tootlusel.
      //https://enos.itcollege.ee/~ylari/I231/Node.java?fbclid=IwAR3z6TOepUDiL-6CKe-qVaFOg5_vq_CmiTaKsdOv8hMbJ12jQ7A0e6ZXSLg
      // natuke abiks. aitas luua ideed.

      /** Just making exeptions for if the string input is incorrect! */

      if (s.contains("\t"))
         throw new RuntimeException("There can't be a tab in string: "+s);
      else if (s.contains("((") && s.contains("))"))
         throw new RuntimeException("Can't contain several brackets in string: "+s);
      else if (s.contains(" "))
         throw new RuntimeException("There cant be an empty spot in string: "+s);
      else if (!s.contains("(") && !s.contains(")") && s.contains(","))
         throw new RuntimeException("There have to be brackets in string: "+s);
//      else if (!s.contains("(") && !s.contains(")")) throw new RuntimeException(s+ " doesnt contain any brackets!");
      else if (!s.contains("(")) return new Node(s, null, null);


      /** splitting string for rework*/
      String[] stringToken = s.split("");
      Stack<Node> sNode = new Stack<>();
//      StringTokenizer stringT = new StringTokenizer(s, "()", true);
//      Stack<Node> sNodeX = new Stack<>();

      /** setting up a HeadNode*/
      Node headNode = new Node(null, null, null);

      /** LEVEL*/
      int level = 0;

      /** Was trying to do a BackWards loop for reading nodes and string but could not get rid of a
       * wrong order problem. So it is easier to just do it the right way instead.*/

      /** Questions about the first solution. */

      /** Third solution. */


//      String s = "(B1,C)A";
//      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < stringToken.length; i++) {
         String token = stringToken[i];
         switch (token) {
            case "(":
               level++;
               /** Testing for errors. */
               if (stringToken[i+1].equals(",")) throw new RuntimeException("Comma can't be placed there -> "
                       + stringToken[i] + stringToken[i+1]+ stringToken[i+2]+ " <- in input: "+ s);
               if (stringToken[i+1].equals(")")) throw new RuntimeException("Colon can't be placed there -> "
                       + stringToken[i] + stringToken[i+1]+ stringToken[i+2]+ " <- in input: "+ s);
               if (i > 0 && !stringToken[i-1].equals(",") && !stringToken[i-1].equals("("))
                  throw new RuntimeException(token+" must be followerd by '(' or ',' in string: "+ s);
               sNode.push(headNode);
               headNode.nextSibling = new Node(null, null, null);
               headNode = headNode.nextSibling;
//               sb.setLength(0);
               break;
            case ")":
               level--;
               if (stringToken[i-1].equals("(") || stringToken[i-1].equals(")") || stringToken[i-1].equals(","))
                  throw new RuntimeException("--> "+s+" <--cannot be followed by '(',')' or ','! in string'" + s
                          + "'!");
//               sb.setLength(0);
               headNode = sNode.pop();
               break;
            case ",":
               /** Testing for errors. */
               if (stringToken[i+1].equals(",")) throw new RuntimeException("Comma can't be placed there -> "
                       + stringToken[i] + stringToken[i+1]+ stringToken[i+2]+ " <- in input: "+ s);
               if (sNode.empty()) throw new RuntimeException("Cant do operations with empty stack");
               if (stringToken[i -1].equals(",")) throw new RuntimeException("Comma can't be placed there -> "
                       + stringToken[i] + stringToken[i-11]+ stringToken[i+1]+ " <- in input: "+ s);
               if (stringToken[i-1].equals("{") || stringToken[i -1].equals(")"))
                  throw new RuntimeException(stringToken[i-1] + " can't be placed there -> "
                       + stringToken[i] + stringToken[i-1]+ stringToken[i+1]+ " <- in input: "+ s);
               headNode.firstChild = new Node(null, null, null);
               headNode = headNode.firstChild;
//               sb.setLength(0);
               if (level == 0) throw new RuntimeException("comma can't ple placed there, at root level "+s);
               break;
            default:
//               sb.append(stringToken[i]);
               /** Getting the "nodes" inside the string.*/
               if (headNode.name == null) headNode.name = token;
               else
                  /** If the "node" has a number in it, just add it. */
                  headNode.name += token;
               break;
         }
      }
      if (level!=0)throw new RuntimeException("You have wrong set of brackets in input--> "+s);

      return headNode;
   }
         public String leftParentheticRepresentation() {

            StringBuilder sb = new StringBuilder();
            sb.append(name);
            if (nextSibling != null) {sb.append("(");
               sb.append(nextSibling.leftParentheticRepresentation());
               sb.append(")");}

            if (firstChild != null) {
               sb.append(",");
               sb.append(firstChild.leftParentheticRepresentation());
            }
            return sb.toString();
         }


         public static void main (String[] param) {
//            String s = "(B1,C)G1"; //oige
//            String s = "((A)E,(B)C)D"; //oige
//            String s = "(((G,H)D,E,(I)F)B,(J)C)A"; // ==> A(B(D(G,H),E,F(I)),C(J)) //oige
//            String s = "((A),(B)C)D";
//            String s = "((A)B(C)D)E";
//            String s = "((A)(C)D)E";
            String s = "((A)8,((B)t,i)C)D";
            Node t = Node.parsePostfix (s);
            String v = t.leftParentheticRepresentation();
            System.out.println (s + " ==> " + v); // (B1,C)A ==> A(B1,C)
         }

         //C-9.7

      }
