/**
 * Menu - this interface will help define our Menu.
 *
 * @author Uriel Schwell
 * @version 11.06.2018
 */

/**
 *
 * @param <T> -task to perform.
 */
    public interface Menu<T> extends Animation {
        void addSelection(String key, String message, T returnVal);
        T getStatus();

    /**
     * add a sub-menu.
     * @param key -selectionKey.
     * @param message -
     * @param subMenu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    }
