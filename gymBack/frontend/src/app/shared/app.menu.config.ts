import {
  MenuRootItem,

} from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  { id: 'clients', name: 'CLIENTS', icon: 'people', route: '/main/clients' },
  { id: 'rooms', name: 'ROOMS', icon: 'room', route: '/main/rooms' },
  { id: 'classes', name: 'CLASSES', icon: 'class', route: '/main/classes' },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'logout', confirm: 'yes' }
];
