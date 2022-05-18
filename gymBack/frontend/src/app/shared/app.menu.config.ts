import {
  MenuRootItem,

} from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  { id: 'clients', name: 'CLIENTS', icon: 'people', route: '/main/clients' },
  { id: 'classes', name: 'CLASSES', icon: 'class', route: '/main/classes' },
  { id: 'assign-classes', name: 'ASSING CLASSES', icon: 'grid_view', route: '/main/assign-classes' },
  { id: 'assign-clients', name: 'ASSING CLIENTS', icon: 'grid_view', route: '/main/assign-clients' },
  { id: 'rooms', name: 'ROOMS', icon: 'room', route: '/main/rooms' },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'logout', confirm: 'yes' }
];
