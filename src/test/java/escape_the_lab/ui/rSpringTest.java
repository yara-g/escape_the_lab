package escape_the_lab.ui;

public class rSpringTest {
//    private rSpring game;
//    private SpringLab mockLab;
//    private Inventory inventory;
//    private LifeManager lifeManager;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        CountDownLatch latch = new CountDownLatch(1);
//
//        Platform.startup(() -> {
//        }); // Starts JavaFX platform if not already started
//
//        Platform.runLater(() -> {
//            try {
//                Stage stage = new Stage();
//                mockLab = new SpringLab(stage);
//                inventory = new Inventory();
//                lifeManager = new LifeManager();
//                Overlay mockOverlay = new Overlay(inventory, lifeManager);
//                game = new rSpring(stage, mockLab, mockOverlay);
//            } finally {
//                latch.countDown();
//            }
//        });
//
//        latch.await();
//    }
//
//    @Test
//    public void testInitialFlags() {
//        assertFalse(game.isDoorUnlocked, "Door should initially be locked");
//        assertFalse(game.springPlaced, "Spring should not be placed at start");
//        assertFalse(game.massPlaced, "Mass should not be placed at start");
//    }
//
//    @Test
//    public void testImageViewsNotNull() {
//        assertNotNull(game.light);
//        assertNotNull(game.door);
//        assertNotNull(game.mainChair);
//        assertNotNull(game.mainDrawer);
//        assertNotNull(game.mainShelves);
//        assertNotNull(game.person);
//        assertNotNull(game.shadow);
//        assertNotNull(game.bgMain);
//        assertNotNull(game.obj1);
//        assertNotNull(game.obj2);
//        assertNotNull(game.chair);
//        assertNotNull(game.chairCover);
//        assertNotNull(game.spring1);
//        assertNotNull(game.spring2);
//        assertNotNull(game.spring3);
//        assertNotNull(game.springStand);
//        assertNotNull(game.table);
//        assertNotNull(game.obj1Low);
//        assertNotNull(game.obj1High);
//        assertNotNull(game.obj2Low);
//        assertNotNull(game.obj2High);
//        assertNotNull(game.obj3Low);
//        assertNotNull(game.obj3High);
//        assertNotNull(game.springLow);
//        assertNotNull(game.springHigh);
//    }
//
//    @Test
//    public void testItemInitialization() {
//        assertNotNull(game.placeHolder);
//        assertEquals("Place Holder", game.placeHolder.getName());
//
//        assertEquals("100N/m", game.spring1Item.getName());
//        assertEquals("200N/m", game.spring2Item.getName());
//        assertEquals("300N/m", game.spring3Item.getName());
//        assertEquals("5kg", game.mass1Item.getName());
//        assertEquals("3kg", game.mass2Item.getName());
//        assertEquals("4kg", game.mass3Item.getName());
//    }
//
//    @Test
//    public void testMonologueListsStartEmpty() {
//        assertTrue(game.monologues.isEmpty());
//        assertTrue(game.monologuesF.isEmpty());
//        assertTrue(game.monologuesL.isEmpty());
//    }
}