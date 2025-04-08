# Fizz-buzz exercise

## Architecture

The service has been architected in a Hexagonal pattern or Ports and Adapters pattern. This allows for a clean
separation of concerns and makes it easy to swap out different components of the system without affecting the core
logic and also a nice way to test separate modules easily.

```mermaid
graph TD
%% External Actors
    subgraph External ["External World"]
        Client["HTTP Client"]
    end

%% Inbound Adapters Layer
    subgraph InboundAdapters ["Inbound Adapters"]
        GetFizzBuzzImpl["GetFizzBuzzImpl\n(REST Controller)"]
        GetStatsImpl["GetStatsImpl\n(REST Controller)"]
    end

%% Inbound Ports Layer
    subgraph InboundPorts ["Inbound Ports"]
        GetFizzBuzz["GetFizzBuzz\n(Interface)"]
        GetStats["GetStats\n(Interface)"]
    end

%% Domain Layer
    subgraph Domain ["Domain"]
        subgraph Models ["Domain Models"]
            FizzBuzzRequest["FizzBuzzRequest"]
            StatsData["StatsData"]
        end

        subgraph Services ["Domain Services"]
            FizzBuzzService["FizzBuzzService"]
            StatsService["StatsService"]
        end
    end

%% Outbound Ports Layer
    subgraph OutboundPorts ["Outbound Ports"]
        StatsStore["StatsStore\n(Interface)"]
    end

%% Outbound Adapters Layer
    subgraph OutboundAdapters ["Outbound Adapters"]
        StatsStoreImpl["StatsStoreImpl"]
        DB[("Storage")]
    end

%% Connections
    Client -->|HTTP Request| GetFizzBuzzImpl & GetStatsImpl
    GetFizzBuzzImpl -.->|implements| GetFizzBuzz
    GetStatsImpl -.->|implements| GetStats
    GetFizzBuzz -->|uses| FizzBuzzService
    GetStats -->|uses| StatsService
    FizzBuzzService -->|uses| StatsStore
    StatsService -->|uses| StatsStore
    StatsStore -.-|implements| StatsStoreImpl
    StatsStoreImpl -->|persists| DB
```
