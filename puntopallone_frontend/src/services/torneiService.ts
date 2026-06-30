import api from './api';
import type { Torneo } from '../types/Torneo';

export interface TorneiFilters {
    nome?: string;
    annoMin?: number;
    annoMax?: number;
}

export const torneiService = {
    getAll: async (filters?: TorneiFilters): Promise<Torneo[]> => {
        const params = new URLSearchParams();
        if (filters?.nome) params.append('nome', filters.nome);
        if (filters?.annoMin) params.append('annoMin', String(filters.annoMin));
        if (filters?.annoMax) params.append('annoMax', String(filters.annoMax));

        const response = await api.get<Torneo[]>('/tornei', { params });
        return response.data;
    },
};