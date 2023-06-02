import org.apache.commons.text.similarity.FuzzyScore;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 The Building class represents a building with points of interest (POIs).
 It provides a graphical user interface for the user to search for and view POIs on a map.
 @author Daniel, Ethan, Shuja
 @version 1.2
 @since 4/1/2023
 */
public abstract class Building{
    ArrayList<pointsOfInterest> poiList;
    ArrayList<pointsOfInterest>favList;

    protected JFrame frame;
    protected JFrame metadataFrame;
    protected JPanel optionsPanel;
    protected JPanel mainbuttonPanel;
    protected JPanel searchPanel;
    protected BufferedImage mapImage;
    protected JPanel imagePanel;
    protected int currentFloor;
    protected JButton searchButton;

    protected boolean isAdmin;
    protected PoiMaking maker;

    protected BufferedImage floor1image;
    protected BufferedImage floor2image;
    protected BufferedImage floor3image;
    protected BufferedImage floor4image;
    protected BufferedImage floor5image;

    protected JTextField searchField;

    protected FuzzyScore fuzzyScore;
    protected final int zoomValue = 50;

    protected JButton backButton;
    protected JButton helpButton;
    protected JButton addPOIButton;
    protected JScrollPane scrollPane;

    protected JComboBox<String> resultDropdown;
    protected pointsOfInterest.buildings buildingType;

    JCheckBox washroomsCheckbox;
    JCheckBox classroomsCheckbox;
    JCheckBox stairwellsCheckbox;
    JCheckBox elevatorsCheckbox;
    JCheckBox entryCheckbox;
    JCheckBox exitCheckbox;
    JCheckBox genLabsCheckbox;
    JCheckBox restaurantsCheckbox;
    JCheckBox csLabsCheckbox;
    JCheckBox csCommonRoomCheckbox;
    JCheckBox userPOICheckbox;

    private JButton selectedPOI = null;
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;
    private JTree poiTree;
    private JSONObject userData;

    /**
     * Constructor for Building class.
     *
     * @param title the title of the building
     * @param userData the user data
     * @throws IOException if there is an I/O error
     */
    protected void Building(String title,  JSONObject userData) throws IOException {

        this.isAdmin = userData.getBoolean("isAdmin");
        this.userData =userData;
        if(isAdmin){
            System.out.println("Adminnn!!!!!");
        }
        currentFloor = 1;
        mainbuttonPanel = new JPanel();
        backButton = new JButton("Previous");
        helpButton = new JButton("Help!");
        addPOIButton = new JButton("Add POI");
        frame = new JFrame(title);
        frame.setSize(new Dimension(1500, 1000));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        poiList = new ArrayList<>();
        maker = new PoiMaking(userData);
        poiList = maker.getUserPOIs();
        favList = new ArrayList<>();
        favList = maker.getFavoriteList();

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        washroomsCheckbox = new JCheckBox("Washrooms");
        classroomsCheckbox = new JCheckBox("Classrooms");
        stairwellsCheckbox = new JCheckBox("Stairwells");
        elevatorsCheckbox = new JCheckBox("Elevators");
        entryCheckbox = new JCheckBox("Entry");
        exitCheckbox = new JCheckBox("Exit");
        genLabsCheckbox = new JCheckBox("Gen Labs");
        restaurantsCheckbox = new JCheckBox("Restaurants");
        csLabsCheckbox = new JCheckBox("CS Labs");
        csCommonRoomCheckbox = new JCheckBox("CS Common Room");
        userPOICheckbox = new JCheckBox("User POI");
        optionsPanel.add(washroomsCheckbox);
        optionsPanel.add(classroomsCheckbox);
        optionsPanel.add(stairwellsCheckbox);
        optionsPanel.add(elevatorsCheckbox);
        optionsPanel.add(entryCheckbox);
        optionsPanel.add(exitCheckbox);
        optionsPanel.add(genLabsCheckbox);
        optionsPanel.add(restaurantsCheckbox);
        optionsPanel.add(csLabsCheckbox);
        optionsPanel.add(csCommonRoomCheckbox);
        //optionsPanel.add(userPOICheckbox);

        washroomsCheckbox.setSelected(true);
        classroomsCheckbox.setSelected(true);
        stairwellsCheckbox.setSelected(true);
        elevatorsCheckbox.setSelected(true);
        entryCheckbox.setSelected(true);
        exitCheckbox.setSelected(true);
        genLabsCheckbox.setSelected(true);
        restaurantsCheckbox.setSelected(true);
        csLabsCheckbox.setSelected(true);
        csCommonRoomCheckbox.setSelected(true);
        userPOICheckbox.setSelected(true);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(optionsPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Add the tasks you want to perform before the program closes
                maker.saveData(poiList,favList);
                System.exit(0);
            }
        });

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        washroomsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor, buildingType);
            }
        });
        classroomsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        stairwellsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        elevatorsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        entryCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        exitCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        genLabsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        restaurantsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        csLabsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        csCommonRoomCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });
        userPOICheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOIsOnMap(currentFloor,buildingType);
            }
        });

        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                AffineTransform at = new AffineTransform();
                at.scale(zoomValue / 100.0, zoomValue / 100.0);
                g2d.drawImage(mapImage, at, null);
            }

        };
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainPage(userData);
                maker.saveData(poiList,favList);
                if (metadataFrame != null) metadataFrame.setVisible(false);
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpPageM(userData);

            }
        });

        addPOIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MouseListener addPoiMouseListener = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        // Inside the MouseAdapter's mouseClicked method
                        int x = (int) (e.getX() / (zoomValue / 100.0));
                        int y = (int) (e.getY() / (zoomValue / 100.0));


                        // 4. Show dialog to get input
                        JTextField nameField = new JTextField();
                        JTextField descriptionField = new JTextField();
                        JComboBox<pointsOfInterest.categories> categoriesComboBox = new JComboBox<>(pointsOfInterest.categories.values());
                        // Modify the dialog in the ActionListener of the Add POI button
                        JTextField roomNumberField = new JTextField();
                        Object[] message = {
                                "Name:", nameField,
                                "Description:", descriptionField,
                                "Category:", categoriesComboBox,
                                "Room Number:", roomNumberField,
                        };

                        int option = JOptionPane.showConfirmDialog(frame, message, "Add POI", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String name = nameField.getText();
                            String description = descriptionField.getText();
                            pointsOfInterest.categories category = (pointsOfInterest.categories) categoriesComboBox.getSelectedItem();

                            // 5. Create new POI object and add it to the poiList
                            String roomNumber = roomNumberField.getText();

                            // Create new POI object and add it to the poiList
                            pointsOfInterest newPOI = new pointsOfInterest(name, x, y, category, roomNumber, currentFloor, buildingType);
                            newPOI.setDescription(description);
                            newPOI.setDefault(false);
                            poiList.add(newPOI);
                            updatePOIsOnMap(currentFloor,buildingType);
                            updateTree();
                        }

                        // 6. Remove custom MouseListener
                        imagePanel.removeMouseListener(this);
                    }
                };

                // 7. Add custom MouseListener to imagePanel
                imagePanel.addMouseListener(addPoiMouseListener);
            }
        });

        mainbuttonPanel.add(backButton);
        mainbuttonPanel.add(helpButton);
        mainbuttonPanel.add(addPOIButton);
        frame.add(mainbuttonPanel, BorderLayout.SOUTH);

        int panelWidth = (int) (mapImage.getWidth() * (zoomValue / 100.0));
        int panelHeight = (int) (mapImage.getHeight() * (zoomValue / 100.0));
        imagePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        scrollPane = new JScrollPane(imagePanel);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getHorizontalScrollBar().setMaximum(panelWidth);
        scrollPane.getVerticalScrollBar().setMaximum(panelHeight);
        frame.add(scrollPane, BorderLayout.CENTER);


        resultDropdown = new JComboBox<>();
        resultDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPOIName = (String) resultDropdown.getSelectedItem();
            }
        });
        resultDropdown.setEditable(false);
        resultDropdown.setEnabled(false);

        // Set up the search field

        fuzzyScore = new FuzzyScore(Locale.ENGLISH);
        searchField = new JTextField();
        searchField.setColumns(20);

        //setContentPane(contentPane);
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String query = searchField.getText();
                    performSearch(query);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    String query = searchField.getText();
                    performSearch(query);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Set up the search button
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPOI = (String) searchField.getText();
                performSearch(selectedPOI);
            }
        });
        searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(resultDropdown);
        searchPanel.add(searchButton);
        frame.add(searchPanel, BorderLayout.NORTH);

        // poi discovery

        // Create the root node of the tree

        rootNode = new DefaultMutableTreeNode("POI Discovery");

        DefaultMutableTreeNode favNode = new DefaultMutableTreeNode("Favourites");
        rootNode.add(favNode);
        for (pointsOfInterest.categories category : pointsOfInterest.categories.values()) {

            // Add the POI objects to the appropriate category nodes
            for (pointsOfInterest poi : favList) {
                if (poi.getCategory() == category && poi.getFloor() == currentFloor &&  poi.getBuilding() == buildingType) {
                    DefaultMutableTreeNode poiNode = new DefaultMutableTreeNode(poi);
                    favNode.add(poiNode);
                }
            }
        }
        // Add child nodes for each POI category from the enum
        DefaultMutableTreeNode poisNode = new DefaultMutableTreeNode("Points of Interest");
        rootNode.add(poisNode);
        for (pointsOfInterest.categories category : pointsOfInterest.categories.values()) {

            // Add the POI objects to the appropriate category nodes
            for (pointsOfInterest poi : poiList) {
                if (poi.getCategory() == category && poi.getFloor() == currentFloor &&  poi.getBuilding() == buildingType) {
                    DefaultMutableTreeNode poiNode = new DefaultMutableTreeNode(poi);
                    poisNode.add(poiNode);
                }
            }
        }
        DefaultMutableTreeNode userPoisNode = new DefaultMutableTreeNode("User POI");
        rootNode.add(userPoisNode);
        for (pointsOfInterest.categories category : pointsOfInterest.categories.values()) {
            for (pointsOfInterest poi : poiList) {
                if (poi.getCategory() == category && poi.getFloor() == currentFloor && poi.getBuilding() == buildingType && !poi.isDefault()) {
                    DefaultMutableTreeNode poiNode = new DefaultMutableTreeNode(poi);
                    userPoisNode.add(poiNode);
                }
            }
        }
        // Create the tree model and tree component
        treeModel = new DefaultTreeModel(rootNode);
        poiTree = new JTree(treeModel);
        poiTree.setRootVisible(false);

        // Set a custom cell renderer to display POI names and icons
        poiTree.setCellRenderer(new POITreeCellRenderer());
        poiTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TreePath path = poiTree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node.getUserObject() instanceof pointsOfInterest) {
                            pointsOfInterest poi = (pointsOfInterest) node.getUserObject();
                            changeFloor(poi.getFloor());
                            scrollToButton(poi);
                            openPOI(poi);
                        }
                    }
                }
            }
        });


        // Add the tree component to the panel

        JScrollPane treeScrollPane = new JScrollPane(poiTree);
        treeScrollPane.setPreferredSize(new Dimension(200,treeScrollPane.getPreferredSize().height));
        frame.add(treeScrollPane, BorderLayout.WEST);

        frame.setVisible(true);
        updatePOIsOnMap(1,buildingType);
    }
    /**
     * Adds a point of interest (POI) icon to the map if it is on the specified floor.
     * The POI is represented by an image file located at poi.getPath(). The image is scaled to 50x50 pixels,
     * and its position on the map is determined by scaling the POI's (x,y) coordinates according to the current zoom value.
     * Clicking on the POI icon selects it and opens a popup with information about the POI.
     * @param poi the point of interest to add
     * @param floor the floor number to add the POI to
     */
    protected void addPOIIconToMap(pointsOfInterest poi, int floor)  {
        if (poi.getFloor() == floor) {
            ImageIcon icon = new ImageIcon(poi.getPath());
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            double scale = (double) zoomValue / 100;
            int x = (int) (poi.getXCoord() * scale);
            int y = (int) (poi.getYCoord() * scale);

            JButton poiIcon = new JButton(scaledIcon);
            poiIcon.setBounds(x, y, 50, 50);
            poiIcon.setOpaque(false);
            poiIcon.setContentAreaFilled(false);
            poiIcon.putClientProperty("poi", poi);

            poiIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedPOI != null && selectedPOI.equals(poiIcon)) {
                        selectedPOI.setBorder(null);
                        selectedPOI = null;
                    } else {
                        if (selectedPOI != null) {
                            selectedPOI.setBorder(null);
                        }
                        selectedPOI = poiIcon;
                        selectedPOI.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                        openPOI(poi);
                    }
                }
            });

            imagePanel.add(poiIcon);
            imagePanel.setComponentZOrder(poiIcon, 0);
        }
    }

    /**

     Overlays a BufferedImage onto the mapImage using the graphics object obtained from mapImage.

     @param overlay the BufferedImage to be overlayed on the mapImage
     */
    protected void overlayImage(BufferedImage overlay) {
        // Get the graphics object from mapImage
        Graphics g = mapImage.getGraphics();

        // Draw the overlay onto the graphics object at (0, 0)
        g.drawImage(overlay, 0, 0, null);

        // Dispose of the graphics object
        g.dispose();

        // Repaint the imagePanel to update the displayed image
        imagePanel.repaint();
    }
    /**

     Checks if the specified category of points of interest is selected by the user.
     @param category the category of points of interest to check
     @return true if the category is selected by the user, false otherwise
     */
    protected boolean isCategorySelected(pointsOfInterest.categories category) {
        switch (category) {
            case washroom:
                return washroomsCheckbox.isSelected();
            case classroom:
                return classroomsCheckbox.isSelected();
            case stairwell:
                return stairwellsCheckbox.isSelected();
            case elevator:
                return elevatorsCheckbox.isSelected();
            case entrance:
                return entryCheckbox.isSelected();
            case exit:
                return exitCheckbox.isSelected();
            case genLab:
                return genLabsCheckbox.isSelected();
            case restaurant:
                return restaurantsCheckbox.isSelected();
            case CsLab:
                return csLabsCheckbox.isSelected();
            case CsCollaborativeRoom:
                return csCommonRoomCheckbox.isSelected();
            case userPOI:
                return userPOICheckbox.isSelected();
            default:
                return false;
        }
    }

    /**

     Updates the points of interest (POIs) on the map for a specified building and floor.
     @param floor an integer representing the floor number of the building
     @param building a buildings enum representing the building where the POIs are located
     */
    protected void updatePOIsOnMap(int floor, pointsOfInterest.buildings building) {
        // Remove all POI icons from imagePanel
        for (Component c : imagePanel.getComponents()) {
            if (c instanceof JButton) {
                imagePanel.remove(c);
            }
        }
        // Add POI icons to imagePanel for specified building and floor if category is selected
        for (pointsOfInterest poi : poiList) {
            if (poi.getBuilding() == building && poi.getFloor() == floor && isCategorySelected(poi.getCategory())) {
                addPOIIconToMap(poi, floor);
            }
        }
        imagePanel.setLayout(null); // Set layout manager to null
        imagePanel.revalidate(); // Revalidate imagePanel
        imagePanel.repaint(); // Repaint imagePanel
    }

    /**

     This method performs a search for points of interest (POIs) based on a given query string.

     It populates a dropdown menu with the names of the POIs that match the query and belong to the specified building type.

     When a POI is selected from the dropdown menu, it changes the floor to the floor of the selected POI, scrolls to the button representing the selected POI, and opens the POI.

     @param query a String representing the search query
     */
    protected void performSearch(String query) {
        // Creates an ArrayList to store the names of the matched POIs and a HashMap to store the matched POIs themselves
        ArrayList<String> matchedNames = new ArrayList<>();
        HashMap<String, pointsOfInterest> matchedPoiMap = new HashMap<>();

        // Searches for POIs that match the query and belong to the specified building type
        for (pointsOfInterest poi : FuzzySearch.searchPOI(query, poiList)) {
            if (poi.getBuilding() == buildingType) {
                // Adds the name of the matched POI to the ArrayList and puts the matched POI itself in the HashMap
                matchedNames.add(poi.getName());
                matchedPoiMap.put(poi.getName(), poi);
            }
        }

        // Populates the resultDropdown with the names of the matched POIs
        resultDropdown.setModel(new DefaultComboBoxModel<>(matchedNames.toArray(new String[matchedNames.size()])));
        // Enables the resultDropdown if there are matched POIs
        resultDropdown.setEnabled(matchedNames.size() > 0);

        // Adds an ActionListener to the resultDropdown to handle POI selection
        resultDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Casts the event source to a JComboBox and gets the selected item (i.e. the name of the selected POI)
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedPoiName = (String) comboBox.getSelectedItem();
                // Gets the POI object from the HashMap using the selected POI's name
                pointsOfInterest selectedPoi = matchedPoiMap.get(selectedPoiName);
                // If the selected POI exists, changes the floor, scrolls to the button, and opens the POI
                if(selectedPoi!= null) {
                    changeFloor(selectedPoi.getFloor());
                    scrollToButton(selectedPoi);
                    openPOI(selectedPoi);
                }
            }
        });
    }
    /**

     This method is used to open a selected point of interest (POI) by setting its border to red and displaying its metadata.

     @param selectedPoi A pointsOfInterest object representing the selected POI.

     @throws NullPointerException if selectedPoi is null.
     */
    protected void openPOI(pointsOfInterest selectedPoi) throws NullPointerException {
        if (selectedPoi != null) {
            // Deselect the previously selected POI if any
            if (selectedPOI != null) {
                selectedPOI.setBorder(null);
            }

            // Find the JButton for the selected POI and set its border to red
            Component[] components = imagePanel.getComponents();
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    pointsOfInterest poi = (pointsOfInterest) button.getClientProperty("poi");
                    if (poi != null && poi.equals(selectedPoi)) {
                        button.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                        selectedPOI = button;
                        break;
                    }
                }
            }

            // Display metadata for the selected POI
            displayMetadata(selectedPoi);
        } else {
            throw new NullPointerException("Selected points of interest cannot be null.");
        }
    }
    /**

     This method is used to scroll the JScrollPane to the specified point of interest on the image panel.

     The point of interest is scaled according to the current zoom level and the JScrollPane is centered on the point.

     @param poi the point of interest to scroll to
     */
    protected void scrollToButton(pointsOfInterest poi) {
        double scale = (double) zoomValue / 100; // calculate the scaling factor based on the current zoom level
        int x = (int) (poi.getXCoord() * scale); // calculate the scaled x-coordinate of the point of interest
        int y = (int) (poi.getYCoord() * scale); // calculate the scaled y-coordinate of the point of interest

        JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, imagePanel); // get the parent JScrollPane of the image panel
        if (scrollPane != null) {
            JViewport viewport = scrollPane.getViewport(); // get the viewport of the scroll pane
            int xOffset = (viewport.getWidth() - 50) / 2; // calculate the horizontal offset required to center the viewport on the point of interest. Assuming button size is 50x50
            int yOffset = (viewport.getHeight() - 50) / 2; // calculate the vertical offset required to center the viewport on the point of interest.

            int newX = x - xOffset; // calculate the new x-coordinate of the viewport based on the offset
            int newY = y - yOffset; // calculate the new y-coordinate of the viewport based on the offset

            // Ensure the x and y values do not exceed the panel bounds
            newX = Math.max(0, Math.min(newX, imagePanel.getWidth() - viewport.getWidth()));
            newY = Math.max(0, Math.min(newY, imagePanel.getHeight() - viewport.getHeight()));

            viewport.setViewPosition(new Point(newX, newY)); // set the new position of the viewport to scroll to the point of interest
        }
    }
    /**

     Displays the metadata for the given Point of Interest (POI) object.

     If there is already a metadataFrame open, it will be closed before

     a new one is created. The metadataFrame is populated with labels showing

     the name and description of the POI and buttons for editing and deleting it.

     If the logged in user has admin privileges or the POI is not a default one,

     the edit button is enabled, otherwise it is disabled.

     A "Set as Favorite" button is also present and is initially set to "Set as Favorite".

     If the POI is already in the user's favorites list, the text of the button will be "Unfavorite"

     instead. Clicking the "Set as Favorite"/"Unfavorite" button will add or remove the POI from

     the user's favorites list and update the text of the button accordingly.

     @param poi The Point of Interest (POI) object for which to display the metadata.
     */
    private void displayMetadata(pointsOfInterest poi) {

        if (metadataFrame != null) {
            metadataFrame.dispose();
        }
        metadataFrame = new JFrame("Metadata Window");
        metadataFrame.setSize(400, 200);
        metadataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        Point poiLocation = selectedPOI.getLocationOnScreen();
        int panelX = (int) poiLocation.getX() - metadataFrame.getWidth() / 2;
        int panelY = (int) poiLocation.getY() - metadataFrame.getHeight();
        metadataFrame.setLocation(panelX, panelY);


        JLabel label = new JLabel("<html><b>Name:</b> " + poi.getName() + "<br><br>" +
                "<b>Description:</b> " + poi.getDescription());
        label.setHorizontalAlignment(JLabel.CENTER);

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // Add this action listener for the favorite button
        JButton favoriteButton = new JButton(isFavourite(poi) ? "Unfavorite" : "Set as Favorite");

        favoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFavorite = isFavourite(poi);
                if(isFavorite)favList.remove(poi);
                else favList.add(poi);
                favoriteButton.setText(!isFavorite ? "Unfavorite" : "Set as Favorite");
                //JOptionPane.showMessageDialog(metadataFrame, "POI " + (!isFavorite ? "added to" : "removed from") + " favorites!");
                updateTree();
            }
        });

        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (poi != null) {
                    JFrame editFrame = new JFrame("Edit POI");
                    editFrame.setSize(400, 300);
                    editFrame.setVisible(true);

                    JPanel editPanel = new JPanel(new GridLayout(4, 2));
                    JLabel nameLabel = new JLabel("Name: ");
                    JTextField nameField = new JTextField(poi.getName());
                    nameField.setColumns(10); // Change the number of columns to adjust the width of the text field
                    JLabel descLabel = new JLabel("Description: ");
                    JTextArea descArea = new JTextArea(poi.getDescription());
                    descArea.setColumns(20);
                    descArea.setRows(5); // Change the number of rows to adjust the height of the text area
                    JScrollPane descScrollPane = new JScrollPane(descArea);
                    JLabel catLabel = new JLabel("Category: ");
                    JComboBox<pointsOfInterest.categories> catBox = new JComboBox<>(pointsOfInterest.categories.values());
                    catBox.setSelectedItem(poi.getCategory());

                    editPanel.add(nameLabel);
                    editPanel.add(nameField);
                    editPanel.add(descLabel);
                    editPanel.add(descScrollPane);
                    editPanel.add(catLabel);
                    editPanel.add(catBox);

                    JButton saveButton = new JButton("Save Changes");

                    JButton setLocationButton = new JButton("Set New Location");
                    editPanel.add(setLocationButton);

                    setLocationButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MouseListener setLocationMouseListener = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    int x = (int) (e.getX() / (zoomValue / 100.0));
                                    int y = (int) (e.getY() / (zoomValue / 100.0));

                                    poi.setxCoord(x);
                                    poi.setyCoord(y);

                                    updatePOIsOnMap(currentFloor, buildingType);
                                    imagePanel.removeMouseListener(this);
                                }
                            };

                            imagePanel.addMouseListener(setLocationMouseListener);
                        }
                    });
                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String newName = nameField.getText();
                            String newDesc = descArea.getText();
                            pointsOfInterest.categories newCat = (pointsOfInterest.categories) catBox.getSelectedItem();

                            poi.setName(newName);
                            poi.setDescription(newDesc);
                            poi.setCategory(newCat);

                            // Repaint to reflect changes
                            updatePOIsOnMap(currentFloor,buildingType);
                            updateTree();
                            editFrame.dispose();
                            metadataFrame.dispose();
                        }
                    });

                    editPanel.add(saveButton);
                    editFrame.add(editPanel);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int confirmed = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this POI?", "Delete POI",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmed == JOptionPane.YES_OPTION) {
                            // Get the POI associated with the selected POI icon
                            pointsOfInterest poiToDelete = (pointsOfInterest) selectedPOI.getClientProperty("poi");
                            // Remove the POI from the list of POIs
                            poiList.remove(poiToDelete);

                            if(isFavourite(poi))favList.remove(poi);
                            // Remove the POI icon from the map
                            imagePanel.remove(selectedPOI);
                            imagePanel.revalidate();
                            imagePanel.repaint();
                            // Deselect the POI and hide the metadata
                            selectedPOI.setBorder(null);
                            selectedPOI = null;
                            //metadataPanel.setVisible(false);
                            metadataFrame.dispose();

                            // Refresh the search bar
                            String currentQuery = searchField.getText();
                            performSearch(currentQuery);
                            updateTree();
                        }
                    }
                });

            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        if(isAdmin || !poi.isDefault()){
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
        }
        buttonPanel.add(favoriteButton);

        JPanel metadataPanel = new JPanel(new BorderLayout());
        metadataPanel.add(label, BorderLayout.CENTER);
        metadataPanel.add(buttonPanel, BorderLayout.SOUTH);

        metadataFrame.add(metadataPanel);
        metadataFrame.setVisible(true);
    }
    /**

     Checks if a given point of interest is in the user's list of favourite points of interest.
     @param poi the point of interest to be checked
     @return true if the point of interest is in the user's list of favourites, false otherwise
     */
    protected boolean isFavourite(pointsOfInterest poi) {
        for (pointsOfInterest p: favList) {
            if (poi == p) return true;
        }
        return false;
    }
    /**

     Changes the current floor of the map, updates the overlay image and points of interest displayed.
     @param floor the floor number to change to
     */
    protected void changeFloor(int floor) {
        currentFloor = floor;
        overlayImage(getFloorImagePath(floor));
        updatePOIsOnMap(currentFloor, buildingType);
    }
    /**

     Returns the image file for the given floor number.
     @param floor the floor number to get the image file for
     @return the image file for the given floor number
     */
    protected BufferedImage getFloorImagePath(int floor) {
        return switch (floor) {
            case 1 -> floor1image;
            case 2 -> floor2image;
            case 3 -> floor3image;
            case 4 -> floor4image;
            case 5 -> floor5image;
            default -> null;
        };
    }
    /**

     Updates the tree structure of points of interest displayed on the map.
     This method first removes all existing child nodes from the root node and then
     adds new child nodes for favourite, general points of interest, and user created points of interest.
     Each child node contains a point of interest object.
     */
    protected void updateTree() {
        rootNode.removeAllChildren();
        DefaultMutableTreeNode favNode = new DefaultMutableTreeNode("Favourites");
        rootNode.add(favNode);
        // Adds all the favourite points of interest to the "Favourites" node
        for (pointsOfInterest.categories category : pointsOfInterest.categories.values()) {
            for (pointsOfInterest poi : favList) {
                if (poi.getCategory() == category && poi.getFloor() == currentFloor && poi.getBuilding() == buildingType) {
                    DefaultMutableTreeNode poiNode = new DefaultMutableTreeNode(poi);
                    favNode.add(poiNode);
                }
            }
        }
        // Adds all the general points of interest to the "Points of Interest" node
        DefaultMutableTreeNode poisNode = new DefaultMutableTreeNode("Points of Interest");
        rootNode.add(poisNode);
        for (pointsOfInterest.categories category : pointsOfInterest.categories.values()) {
            for (pointsOfInterest poi : poiList) {
                if (poi.getCategory() == category && poi.getFloor() == currentFloor && poi.getBuilding() == buildingType) {
                    DefaultMutableTreeNode poiNode = new DefaultMutableTreeNode(poi);
                    poisNode.add(poiNode);
                }
            }
        }
        // Adds all the user created points of interest to the "User POI" node
        DefaultMutableTreeNode userPoisNode = new DefaultMutableTreeNode("User POI");
        rootNode.add(userPoisNode);
        for (pointsOfInterest.categories category : pointsOfInterest.categories.values()) {
            for (pointsOfInterest poi : poiList) {
                if (poi.getCategory() == category && poi.getFloor() == currentFloor && poi.getBuilding() == buildingType && !poi.isDefault()) {
                    DefaultMutableTreeNode poiNode = new DefaultMutableTreeNode(poi);
                    userPoisNode.add(poiNode);
                }
            }
        }
        treeModel.reload();
    }
    /**

     POITreeCellRenderer is a private class that implements the TreeCellRenderer interface to customize the rendering of

     the JTree cells in the Points of Interest Tree view.
     */
    private class POITreeCellRenderer implements TreeCellRenderer {

        //private JLabel label;
        private DefaultTreeCellRenderer defaultRenderer;

        /**

         Constructs a POITreeCellRenderer object and initializes the default renderer for the JTree cells.
         */
        public POITreeCellRenderer() {

            defaultRenderer = new DefaultTreeCellRenderer();
        }
        /**

         Returns the component used as a custom renderer for the given JTree cell.

         @param tree the JTree being rendered

         @param value the value of the cell to render

         @param selected true if the cell is selected

         @param expanded true if the cell is expanded

         @param leaf true if the cell is a leaf node

         @param row the index of the row being rendered

         @param hasFocus true if the cell has focus

         @return the component used to render the cell
         */
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                      boolean expanded, boolean leaf, int row, boolean hasFocus) {

            // Let the default renderer handle the other aspects of the tree node display
            return defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf,
                    row, hasFocus);
        }
    }
}

