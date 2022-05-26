//////////////// FILE HEADER (INCLUDE IN EVERY FILE)//////////////////////////
//
// Title:    P09 Art Gallery
// Course:   CS 300 Spring 2022
//
// Author:   Mitchell Brenner
// Email:    mkbrenner3@@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION///////////////////
//
// Partner Name:    Aidan Carrig
// Partner Email:   acarrig@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP//////////////////////////
//
// Persons:         None
// Online Sources:  None
//
//////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 *
 * @author Mitchell Brenner
 *
 */
public class ArtGalleryTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {

    Artwork artwork1 = new Artwork("name", 2000, 200.0);
    Artwork artwork2 = new Artwork("name", 2000, 200.0);
    Artwork artwork3 = new Artwork("name", 2001, 200.0);
    Artwork artwork4 = new Artwork("name", 2001, 250.0);
    Artwork artwork5 = new Artwork("mitch", 2001, 250.0);

    // 1. Check that .equals works with same artworks
    if(!artwork1.equals(artwork2)){
      return false;
    }

    // 2. Check that .equals works with not same artworks
    if(artwork1.equals(artwork3)){
      return false;
    }

    // 3. Check comparesTo with equal artworks
    if(artwork1.compareTo(artwork2) != 0){
      return false;
    }

    // 4. Check compareTo with artwork with higher year
    if(artwork1.compareTo(artwork3) >= 0){
      return false;
    }

    // 5. Check compareTo with artwork with same year but less cost
    if(artwork3.compareTo(artwork4) >= 0){
      return false;
    }

    // 6. Check compareTo with artwork with same year, same cost different name
    if(artwork4.compareTo(artwork5) <= 0){
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios.
   *
   * (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "".
   *
   * (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output.
   *
   * (3) Try adding another artwork
   * which is smaller that the artwork at the root,
   *
   * (4) Try adding a third artwork which is greater
   * than the one at the root,
   *
   * (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree.
   *
   * For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name.
   *
   * (6) Try adding an artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {

    ArtGallery gallery = new ArtGallery();
    Artwork artwork1 = new Artwork("name", 2000, 200.0);
    Artwork artwork2 = new Artwork("name", 2000, 200.0);
    Artwork artwork3 = new Artwork("name", 2001, 200.0);
    Artwork artwork4 = new Artwork("name", 2001, 250.0);
    Artwork artwork5 = new Artwork("mitch", 1999, 250.0);
    Artwork artwork6 = new Artwork("mitch", 2010, 250.0);

    // 1. Check empty gallery
    if(!gallery.isEmpty()){
      return false;
    }

    // 2. Check add adding 1 artwork to empty gallery
    if(!gallery.addArtwork(artwork3)) return false;
    if(gallery.isEmpty()) return false;
    if(gallery.size() != 1) return false;

    // 3. Check add by adding 1 smaller artwork
    if(!gallery.addArtwork(artwork1)) return false;
    if(gallery.isEmpty()) return false;
    if(gallery.size() != 2) return false;

    // 4. Check add by adding 1 greater than root
    if(!gallery.addArtwork(artwork4)) return false;
    if(gallery.isEmpty()) return false;
    if(gallery.size() != 3) return false;

    // 5. Check add by adding 2 more, one on left subtree and one on right subtree
    if(!gallery.addArtwork(artwork5)) return false;
    if(gallery.isEmpty()) return false;
    if(gallery.size() != 4) return false;

    if(!gallery.addArtwork(artwork6)) return false;
    if(gallery.isEmpty()) return false;
    if(gallery.size() != 5) return false;

    // 6. Check add by adding an artwork already in here
    if(gallery.addArtwork(artwork2)) return false;
    if(gallery.isEmpty()) return false;
    if(gallery.size() != 5) return false;

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios.
   *
   * (1) Create a new ArtworkGallery. Then, check that calling the lookup() method on an empty
   * ArtworkGallery returns false.
   *
   * (2) Consider a ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call
   * lookup() method to search for the artwork having a match at the root of the tree.
   *
   * (3) Then, search for an artwork at the right and left subtrees at different levels considering
   * successful and unsuccessful search operations. Make sure that the lookup() method returns
   * the expected output for every method call.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {

    ArtGallery gallery = new ArtGallery();
    Artwork artwork1 = new Artwork("1", 2010, 200.0);
    Artwork artwork2 = new Artwork("2", 2005, 200.0);
    Artwork artwork3 = new Artwork("3", 2008, 200.0);
    Artwork artwork4 = new Artwork("4", 2015, 250.0);
    Artwork artwork5 = new Artwork("5", 2012, 250.0);
    Artwork artwork6 = new Artwork("6", 2020, 250.0);
    Artwork artwork7 = new Artwork("7", 2017, 250.0);
    Artwork artwork8 = new Artwork("8", 2000, 200.0);

    // 1. Check lookUp with empty gallery
    if(gallery.lookup("1", 2000, 200.0)){

      return false;
    }

    // 2. Check lookUp with nonempty list at root
    gallery.addArtwork(artwork1);
    gallery.addArtwork(artwork2);
    gallery.addArtwork(artwork3);
    gallery.addArtwork(artwork4);
    gallery.addArtwork(artwork5);
    gallery.addArtwork(artwork6);
    gallery.addArtwork(artwork7);
    gallery.addArtwork(artwork8);
    if(!gallery.lookup("1", 2010, 200.0)){
      return false;
    }

    // 3. Check lookUP with nonempty list at left and right subtree
    if(!gallery.lookup("8", 2000, 200.0)){

      return false;
    }
    if(!gallery.lookup("5", 2012, 250.0)){
      return false;
    }
    if(gallery.lookup("10", 2050, 20000)){
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as,
   * (1) ensures that the height of an empty artwork tree is zero.
   * (2) ensures that the height of a tree which consists of only one node is 1.
   * (3) ensures that the height of a ArtworkGallery with the following structure for instance,
   * is 4.
   *                (*)
   *              /    \
   *            (*)    (*)
   *             \    /  \
   *            (*) (*)  (*)
   *                     /
   *                   (*)
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {

    ArtGallery gallery = new ArtGallery();
    Artwork artwork1 = new Artwork("name", 2010, 200.0);
    Artwork artwork2 = new Artwork("name", 2005, 200.0);
    Artwork artwork3 = new Artwork("name", 2008, 200.0);
    Artwork artwork4 = new Artwork("name", 2015, 250.0);
    Artwork artwork5 = new Artwork("mitch", 2012, 250.0);
    Artwork artwork6 = new Artwork("mitch", 2020, 250.0);
    Artwork artwork7 = new Artwork("mitch", 2017, 250.0);

    // 1. Test that empty gallery returns height 0
    if(gallery.height() != 0){
      return false;
    }

    // 2. Test that gallery with one artwork is height 1
    gallery.addArtwork(artwork1);
    if(gallery.height() != 1){
      return false;
    }

    // 3. Test that gallery with structure above returns height 4
    gallery.addArtwork(artwork2);
    gallery.addArtwork(artwork3);
    gallery.addArtwork(artwork4);
    gallery.addArtwork(artwork5);
    gallery.addArtwork(artwork6);
    gallery.addArtwork(artwork7);

    if(gallery.height() != 4){
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    ArtGallery gallery = new ArtGallery();
    Artwork smallest = new Artwork("Castle", 1312, 1200.0);
    Artwork smaller = new Artwork("Ranch", 1500, 1000.0);
    Artwork small = new Artwork("House", 1600, 800.0);
    Artwork medium = new Artwork("Car", 1760, 750.0);
    Artwork big = new Artwork("Truck", 1780, 500.0);
    Artwork bigger = new Artwork("Plane", 1800, 200.0);
    Artwork biggest = new Artwork("Fortress", 1900, 100.0);

    // 1. Test on empty BST
    if(gallery.getBestArtwork() != null) {
      System.out.println("getBest did not throw null on empty BST");
      return false;
    }

    // 2. Test with 1 element
    gallery.addArtwork(medium);
    if(gallery.getBestArtwork().compareTo(medium) != 0) {
      System.out.println("getBestArtwork failed on BST with 1 element");
      return false;
    }

    // 3. Test on normal tree
    gallery.addArtwork(smaller);
    gallery.addArtwork(small);
    gallery.addArtwork(smallest);
    gallery.addArtwork(bigger);
    gallery.addArtwork(big);
    gallery.addArtwork(biggest);
    if(gallery.getBestArtwork().compareTo(biggest) != 0) {
      System.out.println("getBestArtwork failed on normal BST");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios.
   *
   * (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when
   * called on an empty tree.
   *
   * (2) Ensures that the ArtworkGallery.lookupAll() method returns an array list which contains
   * all the artwork satisfying the search criteria of year and cost, when called on a nonempty
   * artwork tree with one match, and two matches and more. Vary your search criteria such that
   * the lookupAll() method must check in left and right subtrees.
   *
   * 3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
   * on a non-empty artwork tree with no search results found.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {

    ArtGallery gallery = new ArtGallery();
    Artwork artwork1 = new Artwork("1", 2000, 200.0);
    Artwork artwork2 = new Artwork("2", 2000, 200.0);
    Artwork artwork3 = new Artwork("3", 2002, 200.0);
    Artwork artwork4 = new Artwork("4", 2001, 250.0);
    Artwork artwork5 = new Artwork("5", 2000, 180.0);
    Artwork artwork6 = new Artwork("6", 2010, 250.0);

    // 1. Check lookUpAll with empty gallery
    if(!gallery.lookupAll(2000, 20.0).toString().equals("[]")){
      return false;
    }

    // 2. Check lookUpAll with nonempty list
    gallery.addArtwork(artwork1);
    gallery.addArtwork(artwork2);
    if(!gallery.lookupAll(2000, 200.0).toString().equals(
            "[[(Name: 1) (Year: 2000) (Cost: $200.0)], [(Name: 2) (Year: 2000) (Cost: $200.0)]]")){
      return false;
    }

    gallery.addArtwork(artwork3);
    gallery.addArtwork(artwork4);
    if(!gallery.lookupAll(2000, 200.0).toString().equals(
            "[[(Name: 1) (Year: 2000) (Cost: $200.0)], [(Name: 2) (Year: 2000) (Cost: $200.0)]]")){
      return false;
    }

    gallery.addArtwork(artwork5);
    gallery.addArtwork(artwork6);
    if(!gallery.lookupAll(2000, 200.0).toString().equals("[[(Name: 5) (Year: 2000) " +
            "(Cost: $180.0)], [(Name: 1) (Year: 2000) (Cost: $200.0)], [(Name: 2) (Year: 2000) " +
            "(Cost: $200.0)]]")){
      return false;
    }

    // 3. check lookUpAll with nonempty list and no matches
    if(!gallery.lookupAll(2050, 10.0).toString().equals("[]")){
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios.
   *
   * (1) Buying artwork that is at leaf node
   *
   * (2) Buying artwork at non-leaf node
   *
   * (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    ArtGallery gallery = new ArtGallery();
    Artwork artwork1 = new Artwork("1", 2010, 200.0);
    Artwork artwork2 = new Artwork("2", 2005, 200.0);
    Artwork artwork3 = new Artwork("3", 2008, 200.0);
    Artwork artwork4 = new Artwork("4", 2015, 250.0);
    Artwork artwork5 = new Artwork("5", 2012, 250.0);
    Artwork artwork6 = new Artwork("6", 2020, 250.0);
    Artwork artwork7 = new Artwork("7", 2017, 250.0);
    Artwork artwork8 = new Artwork("8", 2000, 200.0);

    gallery.addArtwork(artwork1);
    gallery.addArtwork(artwork2);
    gallery.addArtwork(artwork3);
    gallery.addArtwork(artwork4);
    gallery.addArtwork(artwork5);
    gallery.addArtwork(artwork6);
    gallery.addArtwork(artwork7);
    gallery.addArtwork(artwork8);

    // 1. test buy with a leaf node
    gallery.buyArtwork("8", 2000, 200.0);
    if(gallery.lookup("8", 2000, 200.0)){
      return false;
    }
    if(gallery.size() != 7){
      return false;
    }
    if(!gallery.toString().trim().equals("[(Name: 2) (Year: 2005) (Cost: $200.0)]\n" +
            "[(Name: 3) (Year: 2008) (Cost: $200.0)]\n" +
            "[(Name: 1) (Year: 2010) (Cost: $200.0)]\n" +
            "[(Name: 5) (Year: 2012) (Cost: $250.0)]\n" +
            "[(Name: 4) (Year: 2015) (Cost: $250.0)]\n" +
            "[(Name: 7) (Year: 2017) (Cost: $250.0)]\n" +
            "[(Name: 6) (Year: 2020) (Cost: $250.0)]")){
      return false;
    }

    // 2. test buy with a node with 1 child
    gallery.buyArtwork("2", 2005, 200.0);
    if(gallery.lookup("2", 2005, 200.0)){
      return false;
    }
    if(!gallery.toString().trim().equals("[(Name: 3) (Year: 2008) (Cost: $200.0)]\n" +
            "[(Name: 1) (Year: 2010) (Cost: $200.0)]\n" +
            "[(Name: 5) (Year: 2012) (Cost: $250.0)]\n" +
            "[(Name: 4) (Year: 2015) (Cost: $250.0)]\n" +
            "[(Name: 7) (Year: 2017) (Cost: $250.0)]\n" +
            "[(Name: 6) (Year: 2020) (Cost: $250.0)]")){
      return false;
    }

    // 3. test buy with a node with 2 children
    gallery.buyArtwork("4", 2015, 250.0);
    if(gallery.lookup("4", 2015, 250.0)){
      return false;
    }
    System.out.println(gallery);

    ArtGallery newGallery = new ArtGallery();
    Artwork mid = new Artwork("mid", 2000, 200.0);
    Artwork left = new Artwork("left", 1900, 100.0);
    Artwork right = new Artwork("right", 2100, 300.0);
    newGallery.addArtwork(mid);
    newGallery.addArtwork(left);
    newGallery.addArtwork(right);

    newGallery.buyArtwork("mid", 2000, 200.0);
    if(newGallery.lookup("mid", 2000, 200.0)) {
      System.out.println("newGallery still contained old root -- FAILED");
      return false;
    }
    if(!newGallery.toString().trim().equals("[(Name: left) (Year: 1900) (Cost: $100.0)]\n" +
            "[(Name: right) (Year: 2100) (Cost: $300.0)]")){
      return false;
    }

    // 4. Check that it throws a NoSuchElement Exception when passed
    try {
      gallery.buyArtwork("NOTPRESENT", 1000, 1000.0);
      return false;
    } catch (NoSuchElementException e){
      // Passed
    } catch (Exception e){
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   *
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {
    return (testArtworkCompareToEquals() && testAddArtworkToStringSize() && testLookup() &&
            testHeight() && testGetBestArtwork() && testLookupAll() && testBuyArtwork());
  }

  /**
   * Calls the test methods
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

}
