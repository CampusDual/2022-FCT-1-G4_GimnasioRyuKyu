import {
  MenuRootItem,

} from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  { id: 'clients', name: 'clients', icon: 'people', route: '/main/clients' },
  { id: 'classes', name: 'classes', icon: 'class', route: '/main/classes' },
  { id: 'assign-classes', name: 'assing classes', icon: 'grid_view', route: '/main/assign-classes' },
  { id: 'assign-clients', name: 'assing clients', icon: 'grid_view', route: '/main/assign-clients' },
  { id: 'rooms', name: 'rooms', icon: 'room', route: '/main/rooms' },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'logout', confirm: 'yes' }
];
