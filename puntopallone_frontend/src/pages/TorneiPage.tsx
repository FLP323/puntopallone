import React, { useState, useEffect, useCallback } from 'react';
import {
    Container, Typography, TextField, Button, Box,
    List, ListItem, ListItemText, CircularProgress
} from '@mui/material';
import { torneiService, TorneiFilters } from '../services/torneiService';
import { Torneo } from '../types/Torneo';

const TorneiPage: React.FC = () => {
    const [tornei, setTornei] = useState<Torneo[]>([]);
    const [loading, setLoading] = useState(false);
    const [filters, setFilters] = useState<TorneiFilters>({
        nome: '',
        annoMin: undefined,
        annoMax: undefined,
    });

    const fetchTornei = useCallback(async () => {
        setLoading(true);
        try {
            const data = await torneiService.getAll(filters);
            setTornei(data);
        } catch (error) {
            console.error('Errore nel recupero tornei:', error);
        } finally {
            setLoading(false);
        }
    }, [filters]);

    useEffect(() => {
        fetchTornei();
    }, []);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFilters(prev => ({
            ...prev,
            [name]: value === '' ? undefined : (name === 'nome' ? value : Number(value)),
        }));
    };

    const handleSearch = () => {
        fetchTornei();
    };

    const handleReset = () => {
        setFilters({ nome: '', annoMin: undefined, annoMax: undefined });
        torneiService.getAll().then(data => setTornei(data));
    };

    return (
        <Container maxWidth="md" sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>
                Tornei
            </Typography>
            <Box sx={{ display: 'flex', gap: 2, alignItems: 'center', mb: 2 }}>
                <TextField
                    label="Nome torneo"
                    name="nome"
                    value={filters.nome || ''}
                    onChange={handleInputChange}
                    size="small"
                />
                <TextField
                    label="Anno min"
                    name="annoMin"
                    type="number"
                    value={filters.annoMin || ''}
                    onChange={handleInputChange}
                    size="small"
                    slotProps={{ htmlInput: { min: 1871 } }}
                />
                <TextField
                    label="Anno max"
                    name="annoMax"
                    type="number"
                    value={filters.annoMax || ''}
                    onChange={handleInputChange}
                    size="small"
                    slotProps={{ htmlInput: { min: 1871 } }}
                />
                <Button variant="contained" onClick={handleSearch}>Cerca</Button>
                <Button variant="outlined" onClick={handleReset}>Reset</Button>
            </Box>
            {loading ? (
                <CircularProgress />
            ) : (
                <List>
                    {tornei.length === 0 && <Typography>Nessun torneo trovato.</Typography>}
                    {tornei.map(torneo => (
                        <ListItem key={torneo.id} divider>
                            <ListItemText
                                primary={torneo.nome}
                                secondary={`Anno: ${torneo.anno}`}
                            />
                        </ListItem>
                    ))}
                </List>
            )}
        </Container>
    );
};

export default TorneiPage;