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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class models the Artwork Gallery implemented as a binary search tree. The search criteria
 * include the year of creation of the artwork, the name of the artwork and its cost.
 *
 * @author Mitchell Brenner
 */
public class ArtGallery {

  private BSTNode<Artwork> root; // root node of the artwork catalog BST
  private int size; // size of the artwork catalog tree

  /**
   * Checks whether this binary search tree (BST) is empty
   *
   * @return true if this ArtworkGallery is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of artwork pieces stored in this BST.
   *
   * @return the size of this ArtworkGallery
   */
  public int size() {
    return size;
  }

  /**
   * Checks whether this ArtworkGallery contains an Artwork given its name, year, and cost.
   *
   * @param name name of the Artwork to search
   * @param year year of creation of the Artwork to search
   * @param cost cost of the Artwork to search
   * @return true if there is a match with this Artwork in this BST, and false otherwise
   */
  public boolean lookup(String name, int year, double cost) {
    Artwork art = new Artwork(name, year, cost);
    return lookupHelper(art, root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given Artwork in the subtree
   * rooted at current
   *
   * @param target  a reference to a Artwork we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {

    if (current == null) {
      return false;
    }

    if (target.compareTo(current.getData()) == 0) {
      return true;
    }

    if (target.compareTo(current.getData()) < 0) {
      return lookupHelper(target, current.getLeft());
    }

    if (target.compareTo(current.getData()) > 0) {
      return lookupHelper(target, current.getRight());
    }

    return false; // Default return statement added to resolve compiler errors

  }

  /**
   * Adds a new artwork piece to this ArtworkGallery
   *
   * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
   * @return true if the newArtwork was successfully added to this gallery, and returns false if
   * there is a match with this Artwork already stored in gallery.
   * @throws NullPointerException if newArtwork is null
   */
  public boolean addArtwork(Artwork newArtwork) {
    if (root == null) {
      root = new BSTNode<>(newArtwork);
      size++;
      return true;
    } else if (addArtworkHelper(newArtwork, root)) {
      size++;
      return true;
    }
    return false; // Default return statement added to resolve compiler errors
  }

  /**
   * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at current.
   *
   * @param current    The "root" of the subtree we are inserting new Artwork into.
   * @param newArtwork The Artwork to be added to a BST rooted at current.
   * @return true if the newArtwork was successfully added to this ArtworkGallery, false if a match
   * with newArtwork is already present in the subtree rooted at current.
   */
  protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {

    if (newArtwork.compareTo(current.getData()) == 0) {
      return false;
    }

    if (newArtwork.compareTo(current.getData()) < 0) {
      if (current.getLeft() == null) {
        current.setLeft(new BSTNode<>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getLeft());
      }
    }

    if (newArtwork.compareTo(current.getData()) > 0) {
      if (current.getRight() == null) {
        current.setRight(new BSTNode<>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getRight());
      }
    }

    return false; // Default return statement added to resolve compiler errors

  }

  /**
   * Gets the recent best Artwork in this BST (meaning the largest artwork in this gallery)
   *
   * @return the best (largest) Artwork (the most recent, highest cost artwork) in this
   * ArtworkGallery, and null if this tree is empty.
   */
  public Artwork getBestArtwork() {

    if (isEmpty()) {
      return null;
    }

    BSTNode<Artwork> current = root;

    while (current.getRight() != null) {
      current = current.getRight();
    }

    return current.getData(); // Default return statement added to resolve compiler errors
  }

  /**
   * Returns a String representation of all the artwork stored within this BST in the increasing
   * order of year, separated by a newline "\n". For instance
   * <p>
   * "[(Name: Stars, Artist1) (Year: 1988) (Cost: $300.0)]" + "\n" + "[(Name: Sky, Artist1) (Year:
   * 2003) (Cost: $550.0)]" + "\n"
   *
   * @return a String representation of all the artwork stored within this BST sorted in an
   * increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   * name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a ArtworkGallery is provided in the
   * description of the above toString() method.
   *
   * @param current reference to the current Artwork within this BST (root of a subtree)
   * @return a String representation of all the artworks stored in the sub-tree rooted at current in
   * increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   * name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Artwork> current) {
    if (current == null) {
      return "";
    }

    Artwork art = current.getData();

    return "" + toStringHelper(current.getLeft()) +
            "[(Name: " + art.getName() + ") (Year: " + art.getYear()
            + ") (Cost: $" + art.getCost() + ")]\n"
            + toStringHelper(current.getRight()); //

  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   *
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root); // Default return statement added to resolve compiler errors
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   *
   * @param current pointer to the current BSTNode within a ArtworkGallery (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Artwork> current) {
    if (current == null) {
      return 0;
    }
    return 1 + Math.max(heightHelper(current.getLeft()), heightHelper(current.getRight()));
  }

  /**
   * Search for all artwork objects created on a given year and have a maximum cost value.
   *
   * @param year creation year of artwork
   * @param cost the maximum cost we would like to search for a artwork
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   * cost. If no artwork satisfies the lookup query, this method returns an empty arraylist
   */
  public ArrayList<Artwork> lookupAll(int year, double cost) {
    return lookupAllHelper(year, cost, root);
  }

  /**
   * Recursive helper method to look up the list of artworks given their year of creation and a
   * maximum value of cost
   *
   * @param year    the year we would like to search for a artwork
   * @param cost    the maximum cost we would like to search for a artwork
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   * cost stored in the subtree rooted at current. If no artwork satisfies the lookup query,
   * this method returns an empty arraylist
   */
  protected static ArrayList<Artwork> lookupAllHelper(int year, double cost,
                                                      BSTNode<Artwork> current) {

    ArrayList<Artwork> artList = new ArrayList<>();

    if (current == null) {
      return artList;
    }

    Artwork art = current.getData();

    artList.addAll(lookupAllHelper(year, cost, current.getLeft()));
    if (art.getYear() == year && (cost - art.getCost()) >= 0) {
      artList.add(current.getData());
    }
    artList.addAll(lookupAllHelper(year, cost, current.getRight()));

    return artList; // Default return statement added to resolve compiler errors
  }

  /**
   * Buy an artwork with the specified name, year and cost. In terms of BST operation, this is
   * equivalent to finding the specific node and deleting it from the tree
   *
   * @param name name of the artwork, artist
   * @param year creation year of artwork
   * @throws NoSuchElementException with a descriptive error message if there is no Artwork found
   *                                with the buying criteria
   */
  public void buyArtwork(String name, int year, double cost) {
    Artwork artwork = new Artwork(name, year, cost);
    root = buyArtworkHelper(artwork, root);
    size--;
  }

  /**
   * Recursive helper method to buy artwork given the name, year and cost. In terms of BST
   * operation, this is equivalent to finding the specific node and deleting it from the tree
   *
   * @param target  a reference to an Artwork we are searching to remove in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Artwork found
   *                                with the buying criteria in the BST rooted at current
   */
  protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current) {

    // if current == null (empty subtree rooted at current), no match found, throw an exception
    if (current == null) {
      throw new NoSuchElementException("Empty tree");
    }

    // Compare the target to the data at current and proceed accordingly
    if (target.compareTo(current.getData()) == 0) {

      // 1. if current is a leaf
      if (current.getLeft() == null && current.getRight() == null) {
        current = null;
      }
      // 2. if current has one child
      else if (current.getLeft() != null && current.getRight() == null) {
        current = current.getLeft();
      } else if (current.getLeft() == null && current.getRight() != null) {
        current = current.getRight();
      }
      // 3. if current has 2 children
      else {
        BSTNode<Artwork> art = new BSTNode<>(getSuccessor(current));

        art.setRight(current.getRight());
        art.setLeft(current.getLeft());
        current = art;

       current.setRight(buyArtworkHelper(art.getData(), current.getRight()));

      }

    } else if (target.compareTo(current.getData()) < 0) {
      current.setLeft(buyArtworkHelper(target, current.getLeft()));

    } else if (target.compareTo(current.getData()) > 0){
      current.setRight(buyArtworkHelper(target, current.getRight()));
    }

    return current;
  }

  /**
   * Helper method to find the successor of a node while performing a delete operation (buyArtwork)
   * The successor is defined as the smallest key in the right subtree. We assume by default that
   * node is not null
   *
   * @param node node whose successor is to be found in the tree
   * @return return the key of the successor node
   */
  protected static Artwork getSuccessor(BSTNode<Artwork> node) {
    node = node.getRight();
    while (node.getLeft() != null) {
      node = node.getLeft();
    }
    return node.getData();
  }
}
